package internet_store.integration.telegram.tasks.tasks;

import java.util.ArrayList;
import java.util.List;


public class TasksControl {
    private final List<MasterTask> tasksList = new ArrayList<>();

    public MasterTask runTask() {
        return tasksList.stream().filter(MasterTask::isTaskRun).findFirst().orElse(null);
    }

    public void allTasksStop() {
        tasksList.forEach(t -> t.setTaskRun(false));
    }

    public void setTask(MasterTask task) {
        tasksList.add(task);
    }
}