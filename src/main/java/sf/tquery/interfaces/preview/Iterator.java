/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tquery.interfaces.preview;

import sf.tquery.interfaces.sub.AggregateOperation;
import sf.tquery.interfaces.sub.BasicIterator;
import sf.tquery.interfaces.sub.StreamOperation;
import sf.uds.interfaces.util.collection.Collectible;
import sf.uds.interfaces.util.list.Listable;

public interface Iterator<T>
extends Listable<T>,
		Collectible<T>,
		StreamOperation<T>,
		AggregateOperation<T>,
		BasicIterator<T>
{}
