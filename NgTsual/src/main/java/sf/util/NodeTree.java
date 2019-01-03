/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.util;

import org.json.JSONObject;
import sf.uds.common.INodeTree;
import sf.uds.del.IRun_1;

import java.util.*;

public class NodeTree implements INodeTree
{
	private INode _head;
	private Map<Object, INode> reverseMap = new HashMap<>();

	NodeTree(Object key, Object value)
	{
		push(key, value);
	}

	@Override
	public INode head()
	{
		return _head;
	}

	@Override
	public boolean push(Object key, Object value)
	{
		if (key == null || value == null)
			return false;

		if (_head == null) {
			final Node head = new Node(key, reverseMap);
			_head = head;
			final Node child = new Node(value, reverseMap);
			child.parents().add(head);
			head.children().add(child);
			return true;
		}


		if (reverseMap.containsKey(key)) {
			INode parent = reverseMap.get(key);
			if (reverseMap.containsKey(value)) {
				INode child = reverseMap.get(value);
				if (parent.children().contains(child)) {
					return true;
				} else {
					child.parents().add(parent);
					parent.children().add(child);
					return true;
				}
			} else {
				INode child = new Node(value, reverseMap);
				child.parents().add(parent);
				parent.children().add(child);
				return true;
			}
		}

		if (reverseMap.containsKey(value)) {
			INode child = reverseMap.get(value);
			Node parent = new Node(key, reverseMap);
			parent.children().add(child);
			child.parents().add(parent);
			_head = parent;
			return true;
		}

		return false;
	}

	@Override
	public JSONObject toJson()
	{
		final LinkedJSONObject[] jsonObject = {new LinkedJSONObject("root", null)};
		try {
			leftTraversal(null,
					node -> jsonObject[0] = new LinkedJSONObject(node.value().toString(), jsonObject[0]),
					node -> jsonObject[0] = jsonObject[0].parent,
					node -> jsonObject[0].current().put(node.toString(), "RecursiveRoute"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject[0].current();
	}

	@Override
	public void leftTraversal(IRun_1<INode> delegate,
	                          IRun_1<INode> goDeep,
	                          IRun_1<INode> goUp,
	                          IRun_1<INode> firstRecursionFound) throws Exception
	{
		if (_head != null)
			leftTraversal(delegate, _head, new ArrayList<>(), new ArrayList<>(),
					goDeep, goUp, firstRecursionFound);
	}

	private void leftTraversal(IRun_1<INode> delegate, INode current, List<INode> executedNodes, List<INode> skipNodes,
	                           IRun_1<INode> goDeep, IRun_1<INode> goUp, IRun_1<INode> firstRecursionFound) throws Exception
	{
		if (skipNodes.contains(current)) {
			if (firstRecursionFound != null)
				firstRecursionFound.run(current);
			return;
		} else {
			if (delegate != null)
				delegate.run(current);
			skipNodes.add(current);
		}
		if (!executedNodes.contains(current)) executedNodes.add(current);
		if (goDeep != null)
			goDeep.run(current);
		if (current.hasChild())
			for (INode child : current.children())
				leftTraversal(delegate, child, executedNodes, skipNodes, goDeep, goUp, firstRecursionFound);
		if (goUp != null)
			goUp.run(current);
	}

	@Override
	public Iterator<INode> iterator()
	{
		return reverseMap.values().iterator();
	}


	static class IzList<T> extends ArrayList<T>
	{
		@Override
		public String toString()
		{
			return "[" + StringHelper.join(this, ",") + "]";
		}
	}

	static class LinkedJSONObject
	{
		LinkedJSONObject parent;
		private JSONObject current = new JSONObject();

		JSONObject current()
		{
			if (current == null)
				current = new JSONObject();
			return current;
		}

		LinkedJSONObject(String name, LinkedJSONObject parent)
		{
			this.parent = parent;
			if (parent != null)
				parent.current.put(name, current);
		}

	}

	public static class Node implements INode
	{
		private List<INode> _parents;
		private List<INode> _children;
		private Object _value;

		Node(Object value, Map<Object, INode> reverseMap)
		{
			_value = value;
			reverseMap.put(value, this);
		}

		@Override
		public List<INode> parents()
		{
			if (_parents == null) _parents = new IzList<INode>();
			return _parents;
		}

		@Override
		public Collection<INode> children()
		{
			if (_children == null) _children = new IzList<INode>();
			return _children;
		}

		@Override
		public Object value()
		{
			return _value;
		}

		@Override
		public INode findChild(Object value)
		{
			if (_children == null) return null;
			for (INode node : _children)
				if (ObjectHelper.requireNotNull(value).equals(node.value()))
					return node;
			return null;
		}

		@Override
		public boolean hasChild()
		{
			return _children != null && _children.size() > 0;
		}

		@Override
		public String toString()
		{
			return _value == null ? Node.super.toString() : _value.toString();
		}


	}

	public static class Hub
	{
		private List<INodeTree> nodeTrees = new IzList<>();

		public void push(Object key, Object value)
		{
			for (INodeTree nodeTree : nodeTrees)
				if (nodeTree.push(key, value))
					return;
			nodeTrees.add(new NodeTree(key, value));
		}

		public String toJson() throws Exception
		{
			relink();
			JSONObject root = new JSONObject();
			for (INodeTree nodeTree : nodeTrees) {
				final JSONObject jsonObject_t = nodeTree.toJson();
				for (String key : jsonObject_t.keySet())
					root.put(key, jsonObject_t.get(key));
			}
			return root.toString();
		}

		private void relink() throws Exception
		{
			final Hub hub = new Hub();
			for (INodeTree tree : nodeTrees)
				tree.leftTraversal((node) ->
				{
					for (INode child : node.children())
						hub.push(node.value(), child.value());
				}, null, null, null);
			nodeTrees = hub.nodeTrees;
		}

		@Override
		public String toString()
		{
			try {
				return toJson();
			} catch (Exception e) {
				return e.toString();
			}
		}
	}
}

