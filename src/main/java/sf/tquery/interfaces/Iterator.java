package sf.tquery.interfaces;

import sf.tquery.interfaces.exec.*;

import java.util.List;
import java.util.Map;

/**
 * 别问我为什么没有toArray
 *
 * @param <T>
 */
public interface Iterator<T>
{
	public List<T> toList() throws Exception;

	public Iterable<T> toIterable() throws Exception;

	/**
	 * 提供T-V类型的转换的转化，在调用Iterable接口时触发
	 *
	 * @param tvTypeConverter T-V类型的转换器
	 * @param <V>             目标类型V
	 * @return V类型的迭代器
	 */
	public <V> Iterator<V> as(ITypeConverter<T, V> tvTypeConverter) throws Exception;

	/**
	 * 提供T-V类型的转换的转化，在调用方法时触发
	 *
	 * @param tvTypeConverter T-V类型的转换器
	 * @param <V>             目标类型V
	 * @return V类型的迭代器
	 */
	public <V> Iterator<V> convert(ITypeConverter<T, V> tvTypeConverter) throws Exception;

	/**
	 * 元素筛选方法，顺序执行，在调用Iterable接口时触发
	 *
	 * @param tSelector T类型的筛选器
	 * @return T类型的迭代器
	 */
	public Iterator<T> where(ISelector<T> tSelector) throws Exception;

	public Iterator<T> any(ISelector<T> tSelector, IRunnable<Iterator<T>> runnable) throws Exception;

	public T first() throws Exception;

	/**
	 * 在序列中顺序寻找特定的第一个元素
	 *
	 * @param tSelector T类型的筛选器
	 * @return T类型结果元素
	 */
	public T first(ISelector<T> tSelector) throws Exception;

	/**
	 * 在序列中顺序寻找特定的第一个元素，如果有结果则执行特定委托
	 *
	 * @param tSelector T类型的筛选器
	 * @param del       入参为T类型结果元素的委托
	 */
	public Iterator<T> first(ISelector<T> tSelector, IRunnable<T> del) throws Exception;

	/**
	 * jre1.8- 补充方法
	 *
	 * @param del 入参为T类型结果元素的委托
	 */
	public Iterator<T> foreach(IRunnable<T> del) throws Exception;

	/**
	 * 直接对结果集操作，保持语法流畅
	 *
	 * @param del 入参为List<T>类型结果元素的委托
	 * @return this
	 */
	public Iterator<T> executeWithList(IRunnable<List<T>> del) throws Exception;

	public Iterator<T> executeWithIterator(IRunnable<Iterator<T>> del) throws Exception;

	public Iterator<T> context(IRunnable<Map> del) throws Exception;

	public <V> Iterator<V> reset(Class<V> vClass, ITypeConverter<Iterator, Iterator<V>> failConverter) throws Exception;

	public <K, V> Iterator<Map.Entry<K, V>> toEntryIterator(ITypeConverter<T, Map.Entry<K, V>> typeConverter) throws Exception;

	public <V> V cast(ITypeConverter<Iterator<T>, V> failConverter) throws Exception;

	public <V> V execute(IAction<V> action) throws Exception;
}
