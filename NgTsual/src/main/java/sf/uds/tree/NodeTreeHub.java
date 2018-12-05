/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.tree;

import org.json.JSONObject;

import java.util.List;

public class NodeTreeHub
{
	private List<INodeTree> nodeTrees = new NodeTree.IzList<INodeTree>();

	public void push(Object key, Object value)
	{
		for (INodeTree nodeTree : nodeTrees)
			if (nodeTree.push(key, value))
				return;
		nodeTrees.add(new NodeTree(key, value));
	}

	public String toJson()
	{
		JSONObject root = new JSONObject();
		for (INodeTree nodeTree : nodeTrees) {
			final JSONObject jsonObject_t = nodeTree.toJson();
			for (String key : jsonObject_t.keySet())
				root.put(key, jsonObject_t.get(key));
		}
		return root.toString();
	}

	@Override
	public String toString()
	{
		return toJson();
	}
}
