package cc2.use_cases.project.domain;


import java.util.List;
import java.util.UUID;

public interface ProjectRepository {

    void add(Project project);

    Project getById(ProjectId projectId);

    List<Project> getAll();

    void delete(ProjectId projectId);

    default ProjectId nextId(){
        return ProjectId.fromUUID(UUID.randomUUID());
    }
    
    
}
