package sf.tquery.interfaces;

public interface Iterable<T>
{
	boolean hasNext() throws Exception;

	T next() throws Exception;

	void reset();
}
