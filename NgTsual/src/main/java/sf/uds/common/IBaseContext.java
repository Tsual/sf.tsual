/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.uds.common;

import java.util.Map;

public interface IBaseContext {
    Map getContext();

    void setContext(Map Context);

    void clearContext();

    <T> T getParam(Class<T> clazz, Object key);

    <T> T getParam(Class<T> clazz, Object key, T defaultValue);
}
