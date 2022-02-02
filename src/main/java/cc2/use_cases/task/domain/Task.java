package cc2.use_cases.task.domain;


import java.util.Objects;

public final class Task {

    private final TaskId taskId;
    private final String name;
    private final Boolean diplomaRequired;

    public Task(TaskId taskId, String name, Boolean diplomaRequired) {
        this.taskId = taskId;
        this.name = Objects.requireNonNull(name);
        this.diplomaRequired = diplomaRequired;
    }

    public static Task of(TaskId taskId, String name, Boolean diplomaRequired){
        return new Task(taskId, name, diplomaRequired);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) && Objects.equals(name, task.name) && Objects.equals(diplomaRequired, task.diplomaRequired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, name, diplomaRequired);
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public Boolean getDiplomaRequired() {
        return diplomaRequired;
    }
}
