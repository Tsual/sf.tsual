package sf.tboot;

public class BootGr
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		String source = "package sf.tboot.temp;\n" +
				"\n" +
				"import sf.uds.util.StringHelper;\n" +
				"\n" +
				"import java.util.Arrays;\n" +
				"\n" +
				"public class Fdsfff\n" +
				"{\n" +
				"\tpublic static void main(String[] args)\n" +
				"\t{\n" +
				"\t\tif (StringHelper.isNotEmpty(\" ff\"))\n" +
				"\t\t\tSystem.out.println(Arrays.asList(\"a\", \"b\"));\n" +
				"\t}\n" +
				"}\n";
		TBootstrapper.load("sf.tboot.temp.Fdsfff", source);
		final Class<?> aClass = Class.forName("sf.tboot.temp.Fdsfff");

		int a = 0;
	}
}
