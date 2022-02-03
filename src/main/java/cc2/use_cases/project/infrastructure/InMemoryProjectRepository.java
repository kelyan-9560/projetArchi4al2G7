package cc2.use_cases.project.infrastructure;

import cc2.use_cases.project.domain.Project;
import cc2.use_cases.project.domain.ProjectId;
import cc2.use_cases.project.domain.ProjectRepository;
import cc2.use_cases.project.domain.exeption.ProjectException;
import cc2.use_cases.task.domain.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class InMemoryProjectRepository implements ProjectRepository {

    private final Map<ProjectId, Project> data = new HashMap<>();

    @Override
    public void create(Project project) {
        final Project exists = getByName(project.getName());
        if(exists != null) return;

        try {
            data.put(project.getProjectId(), project);
        } catch (Exception e) {
            throw ProjectException.failedInsertion();
        }
    }

    @Override
    public Project getById(ProjectId projectId) {
        final Project project = data.get(projectId);
        if (project == null) {
            throw ProjectException.projectNotFound();
        }
        return project;
    }

    @Override
    public Project getByName(String name) {
        final List<Project> projectList = getAll();
        for (Project project: projectList) {
            if (Objects.equals(project.getName(), name)) {
                return project;
            }
        }
        return null;
    }

    @Override
    public List<Project> getAll() {
        if(data.isEmpty()){
            throw ProjectException.noProject();
        }
        return List.copyOf(data.values());
    }

    @Override
    public void delete(ProjectId projectId) {
        try {
            data.remove(projectId);
        } catch (Exception e) {
            throw ProjectException.failedDeletion();
        }
    }

    @Override
    public List<Task> addTask(ProjectId projectId, Task task) {
        final Project project = this.getById(projectId);
        final List<Task> projectTask = project.getTasks();
        projectTask.add(task);

        return projectTask;
    }

    @Override
    public List<Task> getAllTasks(ProjectId projectId, Task task) {
        final Project project = this.getById(projectId);
        return project.getTasks();
    }

    @Override
    public List<Task> removeTask(ProjectId projectId, Task task) {
        final Project project = this.getById(projectId);
        final List<Task> projectTask = project.getTasks();
        projectTask.remove(task);

        return projectTask;
    }
}
