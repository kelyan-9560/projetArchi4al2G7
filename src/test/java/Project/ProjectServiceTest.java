package Project;

import cc2.kernel.CreditCard;
import cc2.kernel.Email;
import cc2.kernel.Location;
import cc2.use_cases.contractor.application.ContractorService;
import cc2.use_cases.contractor.domain.ContractorId;
import cc2.use_cases.contractor.domain.ContractorRepository;
import cc2.use_cases.contractor.infrastructure.InMemoryContractorRepository;
import cc2.use_cases.project.application.ProjectDTO;
import cc2.use_cases.project.application.ProjectService;
import cc2.use_cases.project.domain.Project;
import cc2.use_cases.project.domain.ProjectId;
import cc2.use_cases.project.domain.ProjectRepository;
import cc2.use_cases.project.domain.exeption.ProjectException;
import cc2.use_cases.project.infrastructure.InMemoryProjectRepository;
import cc2.use_cases.task.domain.Task;
import cc2.use_cases.task.domain.TaskId;
import cc2.use_cases.tradesman.application.TradesManService;
import cc2.use_cases.tradesman.domain.*;
import cc2.use_cases.tradesman.infrastructure.InMemoryTradesManRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProjectServiceTest {

    ProjectRepository inMemoryProjectRepository = new InMemoryProjectRepository();
    ProjectService projectService = new ProjectService(inMemoryProjectRepository);


    //TradesMan
    TradesManRepository inMemoryTradesManRepository = new InMemoryTradesManRepository();
    TradesManService tradesManService = new TradesManService(inMemoryTradesManRepository, null);

    final Email email = new Email("kelyan.bervin@gmail.com");
    final TradesManId tradesManId = tradesManService.nextId();
    final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
    final Location location = Location.of("Ile-de-France", "Ermont");
    final List<String> skills = List.of("Java", "TS");

    final DiplomaId diplomaId1 = DiplomaId.of("1");
    final Diploma bac = new Diploma(diplomaId1, "bac", new Date());

    final DiplomaId diplomaId2 = DiplomaId.of("2");
    final Diploma master = new Diploma(diplomaId2, "master", new Date());
    final List<Diploma> diplomas = List.of(bac, master);

    final TradesMan tradesMan = TradesMan.of(tradesManId,"Kélyan", "BERVIN", email,
            creditCard, "Dev", skills, 0.5, location, diplomas);

    //Contractor
    ContractorRepository inMemoryContractorRepository = new InMemoryContractorRepository();
    ContractorService contractorService = new ContractorService(inMemoryContractorRepository, null);
    final ContractorId contractorId = contractorService.nextId();

    //Task
    final TaskId taskId = TaskId.of("1");
    final Task task = Task.of(taskId, "Mur", true);


    //Project
    final ProjectId projectId = projectService.nextId();
    final List<String> jobs = List.of("Maçon", "Chef de projet");
    final List<String> skillsRequiered = List.of("", "Chef de projet");
    final Double dailyTax = 1.4;
    final Integer duration = 15;
    final List<Task> tasks = List.of(task);
    final List<TradesMan> tradesManList = List.of(tradesMan);

    @Test
    void create(){

        final ProjectDTO projectDTO = new ProjectDTO("Test", jobs, skills, location,
                dailyTax, duration, tasks, tradesManList, contractorId);

        ProjectId idProjectCreated = projectService.create(projectDTO);
        Project project = Project.of(idProjectCreated, "Test", jobs, skillsRequiered, location,
                dailyTax, duration, tasks, tradesManList, contractorId);

        Project projectCreated = inMemoryProjectRepository.getById(idProjectCreated);

        assertEquals(project, projectCreated);
    }


    @Test
    void getProjectById(){

        final Project project = Project.of(projectId, "Villa", jobs, skillsRequiered, location,
                                            dailyTax, duration, tasks, tradesManList, contractorId);

        inMemoryProjectRepository.create(project);

        assertEquals(project, projectService.getById(projectId));
    }

    @Test
    void getProjectByName(){

        final Project project = Project.of(projectId, "Villa", jobs, skillsRequiered, location,
                dailyTax, duration, tasks, tradesManList, contractorId);

        inMemoryProjectRepository.create(project);

        assertEquals(project, projectService.getByName(project.getName()));
    }

    @Test
    void delete(){

        final Project project = Project.of(projectId, "Villa", jobs, skillsRequiered, location,
                dailyTax, duration, tasks, tradesManList, contractorId);


        inMemoryProjectRepository.create(project);
        inMemoryProjectRepository.delete(projectId);

        try{
            projectService.getById(projectId);
            fail("Should throw exception");
        } catch (ProjectException projectException){
            assert (true);
        }
    }


    @Test
    void addTask(){
        final TaskId taskId = TaskId.of("1");
        final Task task = Task.of(taskId, "Mur", true);
        final List<Task> tasks = List.of(task);

        final Project project = Project.of(projectId, "Villa", jobs, skillsRequiered, location,
                dailyTax, duration, tasks, tradesManList, contractorId);

        final TaskId taskAddedId = TaskId.of("1");
        final Task taskAdded = Task.of(taskAddedId, "Garage", true);
        final List<Task> newTaskList = List.of(task, taskAdded);

        assertEquals(newTaskList, projectService.addTask(projectId, taskAdded));
    }

    @Test
    void removeTask(){
        final TaskId taskId = TaskId.of("1");
        final Task task = Task.of(taskId, "Mur", true);

        final TaskId taskId2 = TaskId.of("2");
        final Task taskRemoved = Task.of(taskId2, "Garage", true);

        final List<Task> tasks = List.of(task, taskRemoved);

        final Project project = Project.of(projectId, "Villa", jobs, skillsRequiered, location,
                dailyTax, duration, tasks, tradesManList, contractorId);


        final List<Task> newTaskList = List.of(task);

        assertEquals(newTaskList, projectService.removeTask(projectId, taskRemoved));
    }

}
