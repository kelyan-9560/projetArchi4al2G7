package cc2.use_cases.task.application;

public class TaskDTO {

    public String name;
    public Boolean diplomaRequired;

    public TaskDTO(String name, Boolean diplomaRequired) {
        this.name = name;
        this.diplomaRequired = diplomaRequired;
    }
}
