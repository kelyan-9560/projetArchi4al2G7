package cc2.use_cases.task.infrastructure;


import cc2.use_cases.task.domain.Task;
import cc2.use_cases.task.domain.TaskId;
import cc2.use_cases.task.domain.TaskRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTaskRepository implements TaskRepository {

    private final Map<TaskId, Task> data = new HashMap<>();

    @Override
    public void create(Task task) {
        try {
            data.put(task.getTaskId(), task);
        } catch (Exception e) {

        }
    }
}
