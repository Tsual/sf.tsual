/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sf.tboot;

public class Bootstrapper {
    public Bootstrapper() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        check(TbootTest.class, Class.forName("sf.tboot.TbootTest"));
        final Class<?> load = TBootstrapper.load("sf.tboot.TbootTest", str);
        check(TbootTest.class, load, Class.forName("sf.tboot.TbootTest"));
    }

    public static void check(Class... klasses) throws IllegalAccessException, InstantiationException {
        for (Class klass : klasses)
            klass.newInstance();
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
