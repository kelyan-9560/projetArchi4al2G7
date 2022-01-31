package cc2.use_cases.project.domain;

import cc2.use_cases.contractor.domain.Contractor;
import cc2.use_cases.task.domain.Task;
import cc2.use_cases.tradesman.domain.Location;
import cc2.use_cases.tradesman.domain.TradesMan;

import java.util.List;
import java.util.Objects;

public final class Project {

    private final ProjectId projectId;
    private final String name;
    private final List<String> jobs;
    private final List<String> skills;
    private final Location location;
    private final Double dailyTax;
    private final Integer duration;
    private final List<Task> tasks;
    private final List<TradesMan> tradesManList;
    private final Contractor contractor;

    public Project(ProjectId projectId, String name, List<String> jobs, List<String> skills, Location location,
                   Double dailyTax, Integer duration, List<Task> tasks, List<TradesMan> tradesManList, Contractor contractor) {
        this.projectId = Objects.requireNonNull(projectId);
        this.name = Objects.requireNonNull(name);
        this.jobs = Objects.requireNonNull(jobs);
        this.skills = Objects.requireNonNull(skills);
        this.location = Objects.requireNonNull(location);
        this.dailyTax = Objects.requireNonNull(dailyTax);
        this.duration = Objects.requireNonNull(duration);
        this.tasks = Objects.requireNonNull(tasks);
        this.tradesManList = Objects.requireNonNull(tradesManList);
        this.contractor = Objects.requireNonNull(contractor);
    }

    public static Project of(ProjectId projectId, String name, List<String> jobs, List<String> skills, Location location,
                             Double dailyTax, Integer duration, List<Task> tasks, List<TradesMan> tradesManList, Contractor contractor){
        return new Project(projectId, name, jobs, skills, location, dailyTax, duration, tasks, tradesManList, contractor);
    }


    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", name='" + name + '\'' +
                ", jobs=" + jobs +
                ", skills=" + skills +
                ", location=" + location +
                ", dailyTax=" + dailyTax +
                ", duration=" + duration +
                ", tasks=" + tasks +
                ", tradesManList=" + tradesManList +
                ", contractor=" + contractor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(projectId, project.projectId) && Objects.equals(name, project.name) && Objects.equals(jobs, project.jobs) && Objects.equals(skills, project.skills) && Objects.equals(location, project.location) && Objects.equals(dailyTax, project.dailyTax) && Objects.equals(duration, project.duration) && Objects.equals(tasks, project.tasks) && Objects.equals(tradesManList, project.tradesManList) && Objects.equals(contractor, project.contractor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, name, jobs, skills, location, dailyTax, duration, tasks, tradesManList, contractor);
    }

    public ProjectId getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public List<String> getSkills() {
        return skills;
    }

    public Location getLocation() {
        return location;
    }

    public Double getDailyTax() {
        return dailyTax;
    }

    public Integer getDuration() {
        return duration;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<TradesMan> getTradesManList() {
        return tradesManList;
    }

    public Contractor getContractor() {
        return contractor;
    }
}