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

import java.util.List;

public class NodeTreeHub
{
	private List<INodeTree> nodeTrees = new NodeTree.IzList<>();

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
		final NodeTreeHub hub = new NodeTreeHub();
		for (INodeTree tree : nodeTrees)
			tree.leftTraversal((node) ->
			{
				for (INodeTree.INode child : node.children())
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
