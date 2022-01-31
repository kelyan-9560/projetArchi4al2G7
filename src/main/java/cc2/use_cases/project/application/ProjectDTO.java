package cc2.use_cases.project.application;

import cc2.use_cases.contractor.domain.Contractor;
import cc2.use_cases.task.domain.Task;
import cc2.use_cases.tradesman.domain.Location;
import cc2.use_cases.tradesman.domain.TradesMan;

import java.util.List;

public class ProjectDTO {

    public String name;
    public List<String> jobs;
    public List<String> skills;
    public Location location;
    public Double dailyTax;
    public Integer duration;
    public List<Task> tasks;
    public List<TradesMan> tradesManList;
    public Contractor contractor;


    public ProjectDTO(String name, List<String> jobs, List<String> skills, Location location,
                      Double dailyTax, Integer duration, List<Task> tasks, List<TradesMan> tradesManList, Contractor contractor) {
        this.name = name;
        this.jobs = jobs;
        this.skills = skills;
        this.location = location;
        this.dailyTax = dailyTax;
        this.duration = duration;
        this.tasks = tasks;
        this.tradesManList = tradesManList;
        this.contractor = contractor;
    }
}
