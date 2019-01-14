package sf.task.v2;

import java.util.List;

/**
 * 异步任务主机--托管异步任务 线程组生命周期管理 异步任务队列管理
 * 必要方法 创建异步集合对象
 */
public class TaskHost {
    private final boolean[] thread_close = {false};
    private List hubs = null;

    Task execTask(Task task) {
        task.trace.executeTime = System.currentTimeMillis();
        task.v.exec_t = Thread.currentThread();
        task.changeStatus(sf.task.Task.TaskStatus.Executing);

        return task;
    }
}
