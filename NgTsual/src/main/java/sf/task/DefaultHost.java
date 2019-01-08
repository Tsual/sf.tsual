package sf.task;

import sf.uds.del.IRun_1;

public class DefaultHost {
    private static TaskHost host;

    private static TaskHost ensureCreateHost() {
        if (host == null)
            host = new TaskHost("Default", 50, 500, 50L);
        return host;
    }

    public static TaskHub newDefaultHub(IRun_1<String> traceShell) {
        return ensureCreateHost().newTaskHub(150L, traceShell);
    }
}
