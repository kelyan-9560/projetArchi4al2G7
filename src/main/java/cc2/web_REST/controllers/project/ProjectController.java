package cc2.web_REST.controllers.project;


import cc2.use_cases.project.application.ProjectDTO;
import cc2.use_cases.project.application.ProjectService;
import cc2.use_cases.project.domain.ProjectId;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(path = "/project", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody ProjectDTO projectDTO){
        projectService.create(projectDTO);
    }

    @GetMapping(path = "/project", consumes = MediaType.APPLICATION_JSON_VALUE)
    void getById(@RequestParam ProjectId projectId){
        projectService.getById(projectId);
    }

    @GetMapping(path = "/projects", consumes = MediaType.APPLICATION_JSON_VALUE)
    void getAll(){
        projectService.getAll();
    }

    @PostMapping(path = "/delete/project", consumes = MediaType.APPLICATION_JSON_VALUE)
    void delete(@RequestParam ProjectId projectId){
        projectService.delete(projectId);
    }

}
