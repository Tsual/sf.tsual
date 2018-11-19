package sf.tboot;

public class Tester
{
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException
	{
		new TBootClassLoader().loadClass("sf.tboot.Bootstrapper").newInstance();
	}
}
