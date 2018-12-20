package sf.tboot;

public abstract class AutoBootClass {
    static {
        Runtime.getRuntime().loadLibrary("sflib");
    }
}
