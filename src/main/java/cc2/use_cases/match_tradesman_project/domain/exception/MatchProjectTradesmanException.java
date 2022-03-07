package cc2.use_cases.match_tradesman_project.domain.exception;

import cc2.use_cases.project.domain.exeption.ProjectException;
import cc2.use_cases.project.domain.exeption.ProjectExceptionTags;

public final class MatchProjectTradesmanException extends RuntimeException{

    public MatchProjectTradesmanException(int errorCode, MatchProjectTradesmanExceptionTags projectExceptionTags, String message) {
    }

    public static MatchProjectTradesmanException failedInsertion() {
        return new MatchProjectTradesmanException(2, MatchProjectTradesmanExceptionTags.FAILED_INSERTION, "Error in inserting project");
    }

    public static MatchProjectTradesmanException failedDeletion(){
        return new MatchProjectTradesmanException(3, MatchProjectTradesmanExceptionTags.FAILED_DELETION, "Error in deleting matchProjectTradesman");
    }
}
