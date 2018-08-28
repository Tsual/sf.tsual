package sf.tquery.interfaces.v2;

public interface Iterable<T>
{
	boolean hasNext() throws Exception;

	T next() throws Exception;

	void reset();
}
