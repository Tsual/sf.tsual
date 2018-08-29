import sf.tquery.common.Iterators;
import sf.tquery.interfaces.v2.Iterator;
import sf.uds.interfaces.del.executable.IExec_0;

import java.util.*;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		Object res = Iterators.get(new Long[]{0L,1L,2L})
				.where(t->t>2)
				.add(1L)
				

				;


		int a = 0;
	}
}
