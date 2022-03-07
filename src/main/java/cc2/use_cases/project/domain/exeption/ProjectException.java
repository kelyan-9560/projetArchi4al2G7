package cc2.use_cases.project.domain.exeption;


public final class ProjectException extends RuntimeException{

    public ProjectException(int errorCode, ProjectExceptionTags projectExceptionTags, String detail) {
    }

    public static ProjectException projectNotFound(){
        return new ProjectException(1, ProjectExceptionTags.NOT_FOUND, "Project not found");
    }

    public static ProjectException failedInsertion(){
        return new ProjectException(2, ProjectExceptionTags.FAILED_INSERTION, "Error in inserting project");
    }

    public static ProjectException failedDeletion(){
        return new ProjectException(3, ProjectExceptionTags.FAILED_DELETION, "Error in deleting project");
    }

    public static void noProject(){
        System.out.println("Error : 4, Project." + ProjectExceptionTags.NO_PROJECT + ".There is no project");
    }

    public static ProjectException invalidDailyRate(){
        return new ProjectException(5, ProjectExceptionTags.INVALID_DAILY_RATE, " Invalid daily rate");
    }

    public static ProjectException invalidDuration(){
        return new ProjectException(6, ProjectExceptionTags.INVALID_DURATION, " Invalid duration");
    }
}
