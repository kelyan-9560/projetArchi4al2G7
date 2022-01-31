package cc2.use_cases.project.infrastructure;

import cc2.use_cases.project.domain.Project;
import cc2.use_cases.project.domain.ProjectId;
import cc2.use_cases.project.domain.ProjectRepository;
import cc2.use_cases.project.domain.exeption.ProjectException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryProjectRepository implements ProjectRepository {

    private final Map<ProjectId, Project> data = new HashMap<>();

    @Override
    public void add(Project project) {
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
}
