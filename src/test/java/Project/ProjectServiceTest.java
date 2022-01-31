package Project;

import cc2.use_cases.contractor.application.ContractorService;
import cc2.use_cases.contractor.domain.Contractor;
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
    final CreditCard creditCard = new CreditCard("1234567634", "BERVIN", LocalDateTime.now());
    final Location location = new Location("Ile-de-France", "Ermont");

    final TradesMan tradesMan = TradesMan.of(tradesManId,"Kélyan", "BERVIN", email,
            creditCard, "Dev", "Java", 0.5, location, "Bachelor");


    //Contractor
    ContractorRepository inMemoryContractorRepository = new InMemoryContractorRepository();
    ContractorService contractorService = new ContractorService(inMemoryContractorRepository, null);
    final ContractorId contractorId = contractorService.nextId();
    final Contractor contractor = Contractor.of(contractorId,"Kélyan", "BERVIN");

    //Task
    final TaskId taskId = TaskId.of("1");
    final Task task = Task.of(taskId, "Mur");




    @Test
    void create(){

        final ProjectId projectId = projectService.nextId();
        final List<String> jobs = List.of("Maçon", "Chef de projet");
        final List<String> skills = List.of("", "Chef de projet");
        final Double dailyTax = 1.4;
        final Integer duration = 15;
        final List<Task> tasks = List.of(task);
        final List<TradesMan> tradesManList = List.of(tradesMan);

        final ProjectDTO projectDTO = new ProjectDTO("Test", jobs, skills, location,
                dailyTax, duration, tasks, tradesManList, contractor );

        ProjectId idProjectCreated = projectService.create(projectDTO);
        Project project = Project.of(idProjectCreated, "Test", jobs, skills, location,
                dailyTax, duration, tasks, tradesManList, contractor);

        Project projectCreated = inMemoryProjectRepository.getById(idProjectCreated);

        assertEquals(project, projectCreated);
    }


    @Test
    void getProjectById(){

        final ProjectId projectId = projectService.nextId();
        final List<String> jobs = List.of("Maçon", "Chef de projet");
        final List<String> skills = List.of("", "Chef de projet");
        final Double dailyTax = 1.4;
        final Integer duration = 15;
        final List<Task> tasks = List.of(task);
        final List<TradesMan> tradesManList = List.of(tradesMan);


        final Project project = Project.of(projectId, "Villa", jobs, skills, location,
                                            dailyTax, duration, tasks, tradesManList, contractor);

        inMemoryProjectRepository.add(project);

        assertEquals(project, projectService.getById(projectId));

    }

    @Test
    void delete(){

        final ProjectId projectId = projectService.nextId();
        final List<String> jobs = List.of("Maçon", "Chef de projet");
        final List<String> skills = List.of("", "Chef de projet");
        final Double dailyTax = 1.4;
        final Integer duration = 15;
        final List<Task> tasks = List.of(task);
        final List<TradesMan> tradesManList = List.of(tradesMan);


        final Project project = Project.of(projectId, "Villa", jobs, skills, location,
                dailyTax, duration, tasks, tradesManList, contractor);


        inMemoryProjectRepository.add(project);
        inMemoryProjectRepository.delete(projectId);

        try{
            projectService.getById(projectId);
            fail("Should throw exception");
        } catch (ProjectException projectException){
            assert (true);
        }
    }
}
