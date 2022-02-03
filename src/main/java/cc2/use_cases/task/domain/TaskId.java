package cc2.use_cases.task.domain;


import java.util.Objects;
import java.util.UUID;

public final class TaskId {

    private final String id;

    private TaskId(String id) {
        this.id = id;
    }

    public static TaskId of(String id) {
        return new TaskId(id);
    }

    public static TaskId fromUUID(UUID uuid) {
        return new TaskId(uuid.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskId taskId = (TaskId) o;
        return Objects.equals(id, taskId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
