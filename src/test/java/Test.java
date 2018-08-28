import sf.tquery.common.Iterators;
import sf.uds.interfaces.del.executable.IExec_0;

import java.util.*;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		Random ran = new Random();
		List<Long> list = new ArrayList<>();
		for (int i = 0; i < 2000; i++) {
			list.add(ran.nextLong());
		}
		Date date = new Date();
		long l1 = 0l;
		long l2 = 0l;
		long l3 = 0l;
		for (int iii = 0; iii < 50000; iii++) {
			date = new Date();
			sf.tquery.JRE6.Iterators.getIterator(list)
					.where(t -> true)
					.as(arg1 -> arg1 + "")
					.where(t -> true)
					.as(arg1 -> arg1)
					.where(t -> true)
					.as(arg1 -> arg1)
					.where(t -> true)
					.as(arg1 -> arg1)
					.where(t -> true)
					.as(arg1 -> arg1)
					.toList();
			l1 += new Date().getTime() - date.getTime();

			date = new Date();
			Iterators.get(list)
					.where(t -> true)
					.as(arg1 -> arg1 + "")
					.where(t -> true)
					.as(arg1 -> arg1)
					.where(t -> true)
					.as(arg1 -> arg1)
					.where(t -> true)
					.as(arg1 -> arg1)
					.where(t -> true)
					.as(arg1 -> arg1)
					.toList();
			l2 += new Date().getTime() - date.getTime();

			date = new Date();
			for (Long t : list) {
				List<String> stringList = new ArrayList<>();
				String t1 = t + "";
				((IExec_0<Boolean>) () -> true).execute();
				t1 = String.valueOf(t1);
				((IExec_0<Boolean>) () -> true).execute();
				t1 = String.valueOf(t1);
				((IExec_0<Boolean>) () -> true).execute();
				t1 = String.valueOf(t1);
				((IExec_0<Boolean>) () -> true).execute();
				t1 = String.valueOf(t1);
				stringList.add(t1);
			}
			l3 += new Date().getTime() - date.getTime();
			System.out.println(iii);
		}
		System.out.println("1111<<<" + l1);
		System.out.println("2222<<<" + l2);
		System.out.println("3333<<<" + l3);
	}
}
