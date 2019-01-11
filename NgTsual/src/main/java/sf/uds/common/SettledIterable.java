package sf.uds.common;

import java.util.List;

public interface SettledIterable<T> extends Iterable<T> {
    List<T> settleList() throws Exception;
}
