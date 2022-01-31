package cc2.use_cases.task.domain;

import java.util.Objects;

public final class Task {

    private final TaskId taskId;
    private final String name;

    public Task(TaskId taskId, String name) {
        this.taskId = taskId;
        this.name = Objects.requireNonNull(name);
    }

    public static Task of(TaskId taskId, String name){
        return new Task(taskId, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) && Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, name);
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }
}
