/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.common;

import java.util.Map;

public interface IBaseContext<K, V> {
    Map<K, V> getContext();

    void setContext(Map<K, V> Context);

    void clearContext();

    V getParam(Class<V> clazz, K key);

    V getParam(Class<V> clazz, K key, V defaultValue);
}
