package cc2.use_cases.project.domain;


import cc2.use_cases.task.domain.Task;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository {

    void create(Project project);

    Project getById(ProjectId projectId);

    Project getByName(String name);

    List<Project> getAll();

    void delete(ProjectId projectId);

    /**
     * @param projectId
     * @param task
     * @return the updated list of tasks
     */
    List<Task> addTask(ProjectId projectId, Task task);

    List<Task> getAllTasks(ProjectId projectId, Task task);

    List<Task> removeTask(ProjectId projectId, Task task);

    default ProjectId nextId(){
        return ProjectId.fromUUID(UUID.randomUUID());
    }
    
    
}
