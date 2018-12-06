package sf.tquery;

import java.util.LinkedList;
import java.util.List;

public interface Iterable<T>
{
	boolean hasNext() throws Exception;

	T next() throws Exception;

	void reset() throws Exception;

	List[] settle_object = {null};
	default List<T> settle() throws Exception
	{
		if (settle_object[0] == null) {
			List<T> list = new LinkedList<>();
			while (hasNext())
				list.add(next());
			settle_object[0] = list;
		}
		return settle_object[0];
	}
}
