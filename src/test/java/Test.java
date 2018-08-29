import sf.tquery.common.Iterators;
import sf.tquery.interfaces.v2.Iterator;
import sf.uds.interfaces.del.executable.IExec_0;

import java.util.*;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		final List<String> stringList = Iterators.get(new Long[]{0L})
				.where(t -> t > 3L)
				.as(t -> t.toString())
				.toList();
		int a=0;
	}
}
