package hibernate;

import sf.tquery.JRE6.Iterators;

public class Test2
{
	public static void main(String[] args) throws Exception
	{
		Iterators.getIterator(new String[]{"rrr","bbb"})
				.as(t->{throw  new Exception();})
				.foreach(t->t.equals("null"));


	}
}
