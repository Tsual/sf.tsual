/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.common;

import org.json.JSONObject;
import sf.uds.del.IRun_1;

import java.util.Collection;

public interface INodeTree extends Iterable<INodeTree.INode> {
    INode head();

    boolean push(Object key, Object value);

    JSONObject toJson();

    void leftTraversal(IRun_1<INode> delegate,
                       IRun_1<INode> goDeep,
                       IRun_1<INode> goUp,
                       IRun_1<INode> firstRecursionFound) throws Exception;

    interface INode {
        Collection<INode> parents();

        Collection<INode> children();

        Object value();

        INode findChild(Object value);

        boolean hasChild();
    }
}

