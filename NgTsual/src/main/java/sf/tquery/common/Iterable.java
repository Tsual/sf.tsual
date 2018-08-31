package sf.tquery.common;

public interface Iterable<T>
{
	boolean hasNext() throws Exception;

	T next() throws Exception;

	void reset() throws Exception;
}
