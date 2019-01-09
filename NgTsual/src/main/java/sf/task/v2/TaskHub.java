package sf.task.v2;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 异步任务集
 * 包含域 任务集 日志追踪委托
 * 必要方法 执行异步任务 异步等待任务集合
 */
public class TaskHub {
    Queue<Task> tasks = new LinkedBlockingQueue<>();


}
