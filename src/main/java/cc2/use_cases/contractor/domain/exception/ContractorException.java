package cc2.use_cases.contractor.domain.exception;

public final class ContractorException extends RuntimeException {


    public ContractorException(int errorCode, ContractorExceptionTags contractorExceptionTags, String detail) {
    }
    
    public static ContractorException withFirstname(String firstname){
        return new ContractorException(1, ContractorExceptionTags.BAD_FIRSTNAME, "Firstname is bad");
    }

    public static ContractorException withLastname(String lastname){
        return new ContractorException(2, ContractorExceptionTags.BAD_LASTNAME, "Lastname is bad");
    }

    public static ContractorException contractorNotFound(){
        return new ContractorException(2, ContractorExceptionTags.NOT_FOUND, "Contractor not found");
    }

    public static ContractorException failedInsertion(){
        return new ContractorException(3, ContractorExceptionTags.FAILED_INSERTION, "Error in inserting contractor");
    }

    public static ContractorException failedDeletion(){
        return new ContractorException(4, ContractorExceptionTags.FAILED_DELETION, "Error in deleting contractor");
    }

    public static ContractorException noContractor(){
        return new ContractorException(5, ContractorExceptionTags.NO_CONTRACTOR, "There is no contractor");
    }
}
