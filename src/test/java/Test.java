import sf.tquery.common.Iterators;
import sf.tquery.interfaces.v2.Iterator;
import sf.uds.interfaces.del.executable.IExec_0;

import java.util.*;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		Object res = Iterators.get(new Long[]{0L,1L,2L})
				.add(3L)
				.add(6L)
				.add(new Long[0])
				.add(Iterators.get(new Long[]{888L}))
				.add(Arrays.asList(789L))
				.add(new Long[]{866L})
				.add(new Long[0]);


		int a = 0;
	}
}
