package sf.corefr;

import sf.tboot.AutoBootNative;
import sf.uds.interfaces.del.IRun_2;

public class NetCoreCaller extends AutoBootNative {
    public static native void ensureCoreFxRunning();

    public static native Object executeCommand(String cmd, String... args);

    public static native void addFailureHandler(IRun_2<CoreFxr, String[]> failureHandler);
}

