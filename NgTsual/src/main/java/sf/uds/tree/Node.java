/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.tree;

import sf.uds.interfaces.common.INodeTree;
import sf.uds.util.ObjectHelper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Node implements INodeTree.INode
{
	private List<INodeTree.INode> _parents;
	private List<INodeTree.INode> _children;
	private Object _value;

	Node(Object value, Map<Object, INodeTree.INode> reverseMap)
	{
		_value = value;
		reverseMap.put(value, this);
	}

	@Override
	public List<INodeTree.INode> parents()
	{
		if (_parents == null) _parents = new NodeTree.IzList<INodeTree.INode>();
		return _parents;
	}

	@Override
	public Collection<INodeTree.INode> children()
	{
		if (_children == null) _children = new NodeTree.IzList<INodeTree.INode>();
		return _children;
	}

	@Override
	public Object value()
	{
		return _value;
	}

	@Override
	public INodeTree.INode findChild(Object value)
	{
		if (_children == null) return null;
		for (INodeTree.INode node : _children)
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

