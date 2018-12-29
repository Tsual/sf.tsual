package sf.uds.util;

import sf.tquery.Iterators;
import sf.uds.interfaces.del.IExec_1;

import java.util.Random;

public class StringHelper {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNull(String str) {
        return str == null;
    }

    public static boolean inNotNull(String str) {
        return !isNull(str);
    }

    public static String firstCharUpper(String str) {
        return isEmpty(str) ? str : Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String firstCharLower(String str) {
        return isEmpty(str) ? str : Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    public static <T> String join(T[] objs, String jspt, final IExec_1<String, T> exec) {
        try {
            return join(Iterators.get(objs).as(exec::execute).toList(), jspt);
        } catch (Exception e) {
            return join(objs, jspt);
        }
    }

    public static String join(Object[] objs, String jspt) {
        StringBuilder res = new StringBuilder(objs.length > 0 ? objs[0].toString() : "");
        for (int i = 1; i < objs.length; i++)
            res.append(jspt).append(objs[i].toString());
        return res.toString();
    }

    public static <T> String join(java.lang.Iterable<T> objs, String jspt, final IExec_1<String, T> exec) {
        try {
            return join(Iterators.get(objs).as(exec::execute).toList(), jspt);
        } catch (Exception e) {
            return join(objs, jspt);
        }
    }

    public static String join(java.lang.Iterable objs, String jspt) {
        StringBuilder sb = new StringBuilder();
        for (Object o : objs)
            sb.append(jspt).append(o);
        return sb.toString().substring(jspt.length());
    }

    public static String minimize(String str) {
        return str.replace(" ", "")
                .replace("\n", "")
                .replace("  ", "")
                .replace("\t", "");
    }

    public static String randomString(int length) {
        return RandomStringHelper.generate(length);
    }

    private static class RandomStringHelper {
        private static final String CHARS = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMzxcvbnmasdfghkjlqwertyiuop";
        private static final Random ran = new Random();

        private static String generate(int length) {
            StringBuilder sb = new StringBuilder();
            while (length-- > 0)
                sb.append(CHARS.charAt(ran.nextInt(CHARS.length())));
            return sb.toString();
        }
    }
}
