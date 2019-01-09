package sf.task.v2;

import sf.task.TaskStatus;

public class TaskTrace {
    public Long createTime = System.currentTimeMillis(), executeTime, finishTime, abortDuration;
    TaskStatus status = TaskStatus.Created;

    @Override
    public String toString() {
        return "TaskTrace{" +
                "createTime=" + createTime +
                ", executeTime=" + executeTime +
                ", finishTime=" + finishTime +
                ", abortDuration=" + abortDuration +
                ", status=" + status +
                '}';
    }
}

