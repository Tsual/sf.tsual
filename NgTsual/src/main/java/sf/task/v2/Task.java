package sf.task.v2;

import sf.task.TaskStatus;
import sf.task.ThreadLocalOperation;
import sf.uds.del.IExec_0;

/**
 * 异步任务
 * 包含域 执行委托 调用者类 追踪信息 返回结果
 * 必要方法 异步等待 获取结果或异常 取消
 * 状态沿用老版本
 */
public class Task<T> {
    TaskTrace trace = new TaskTrace();
    TaskR<T> r = new TaskR<>();
    TaskV<T> v = new TaskV<>();

    Task(TaskHub hub, IExec_0<T> exec) {
        r.hub = hub;
        v.exec = exec;
    }

    public T awaitResult() throws Exception {
        await();
        if (r.produceException != null) throw r.produceException;
        return r.produceResult;
    }

    public void await() {
        synchronized (v.exec_lock) {
            try {
                if (!r.isProduced) v.exec_lock.wait();
            } catch (Throwable e) {
                return;
            }
        }
    }

    void changeStatus(TaskStatus status) {
        trace.status = status;
    }

    static class TaskR<T> {
        boolean isProduced = false;
        TaskHub hub;
        T produceResult;
        Exception produceException;
        ThreadLocalOperation tlOperation;
    }

    static class TaskV<T> {
        final Object exec_lock = new Object();
        IExec_0<T> exec;
        Thread caller_t;
        Thread exec_t;
        Class caller_klass;
    }

    public TaskTrace getTrace() {
        return trace;
    }
}
