package cc2.use_cases.task.domain;

import java.util.UUID;

public interface TaskRepository {

    void create(Task task);

    default  TaskId nextId(){
        return TaskId.fromUUID(UUID.randomUUID());
    }
}
