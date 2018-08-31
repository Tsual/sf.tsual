package sf.tquery.common;

import sf.tquery.interfaces.exec.IRunnable;
import sf.uds.interfaces.util.list.Listable;

public interface Iterator<T> extends Iterable<T>, Listable<T>, ViIterator<T>
{
	Iterator<T> foreach(IRunnable<T> runnable) throws Exception;

	Iterator<T> settle() throws Exception;
}
