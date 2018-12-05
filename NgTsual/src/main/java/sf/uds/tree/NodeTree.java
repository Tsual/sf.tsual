/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.tree;

import org.json.JSONObject;
import sf.uds.interfaces.common.INodeTree;
import sf.uds.interfaces.del.IRun_1;
import sf.uds.util.StringHelper;

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
}

