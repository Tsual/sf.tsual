/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tboot;

public class Bootstrapper
{
	private static volatile boolean is_init = false;

	public static synchronized void init()
	{
		if (is_init) return;

	}

	public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException
	{
		TBootClassLoader.newInstance().bootClass("sf.tboot.TbootTest");
		check(TbootTest.class, Class.forName("sf.tboot.TbootTest"));
		final Class<?> load = TBootstrapper.load("sf.tboot.TbootTest", str);
		check(TbootTest.class, load, Class.forName("sf.tboot.TbootTest"));
		int a = 0;
	}

	public static void check(Class... classes) throws IllegalAccessException, InstantiationException
	{
		for (Class aClass : classes)
			aClass.newInstance();
	}


	static String str = "package sf.tboot;\n" +
			"\n" +
			"public class TbootTest\n" +
			"{\n" +
			"\tpublic TbootTest()\n" +
			"\t{\n" +
			"\t\tSystem.out.println(\"BBB\");\n" +
			"\t}\n" +
			"}";

}
