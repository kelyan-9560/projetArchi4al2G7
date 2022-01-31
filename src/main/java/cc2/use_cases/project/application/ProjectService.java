package cc2.use_cases.project.application;

import cc2.use_cases.project.domain.Project;
import cc2.use_cases.project.domain.ProjectId;
import cc2.use_cases.project.domain.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectId nextId(){
        return projectRepository.nextId();
    }

    public ProjectId create(ProjectDTO projectDTO){

        final ProjectId projectId = projectRepository.nextId();
        final Project project = Project.of(projectId, projectDTO.name, projectDTO.jobs, projectDTO.skills,
                                            projectDTO.location, projectDTO.dailyTax, projectDTO.duration,
                                            projectDTO.tasks, projectDTO.tradesManList, projectDTO.contractor);

        projectRepository.add(project);
        return projectId;
    }

    public Project getById(ProjectId projectId){
        return projectRepository.getById(projectId);
    }

    public List<Project> getAll(){
        return projectRepository.getAll();
    }


    public void delete(ProjectId projectId){
        projectRepository.delete(projectId);
    }
}
