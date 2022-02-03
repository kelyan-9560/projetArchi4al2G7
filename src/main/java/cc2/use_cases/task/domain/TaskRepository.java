package cc2.use_cases.task.domain;

import java.util.UUID;

public interface TaskRepository {

    default  TaskId nextId(){
        return TaskId.fromUUID(UUID.randomUUID());
    }
}
