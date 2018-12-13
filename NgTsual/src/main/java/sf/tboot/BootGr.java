package sf.tboot;

public class BootGr
{
	public static void main(String[] args)
	{
		String nr = "\r\n"; //回车
		String source = "package sf.temp; " + nr +
				" public class  Hallo{" + nr +
				" public static void main (String[] args){" + nr +
				" System.out.println(\"HelloWorld! 1\");" + nr +
				" }" + nr +
				" }";
		final TbootClassLoader tbootClassLoader = new TbootClassLoader();
		tbootClassLoader.register("sf.temp.Hallo", source);
		Thread thread = Thread.currentThread();
		thread.setContextClassLoader(tbootClassLoader);

		int a = 0;
	}
}
