package cc2.use_cases.tradesman.domain.exception;

public final class TradesManException extends RuntimeException {

    public TradesManException(int errorCode, TradesManExceptionTags tradesManExceptions, String detail) {
    }

    public static TradesManException withFirstname(String firstname){
        return new TradesManException(1, TradesManExceptionTags.BAD_FIRSTNAME, "firstname is bad");
    }

    public static TradesManException withLastname(String lastname){
        return new TradesManException(2, TradesManExceptionTags.BAD_LASTNAME, "lastname is bad");
    }

    public static TradesManException withEmail(String email){
        return new TradesManException(3, TradesManExceptionTags.BAD_EMAIL, email + " is a bad");
    }

    public static TradesManException withJob(String job){
        return new TradesManException(4, TradesManExceptionTags.BAD_JOB, "Job is bad");
    }

    public static TradesManException withSkill(String skill){
        return new TradesManException(5, TradesManExceptionTags.BAD_SKILL, "Skill is bad");
    }

    public static TradesManException withDailyTax(Double dailyTax){
        return new TradesManException(6, TradesManExceptionTags.BAD_DAILY_TAX, "dailyTax is bad");
    }

    public static TradesManException withDiplomas(String diplomas){
        return new TradesManException(7, TradesManExceptionTags.BAD_DIPLOMA,"diplomas is bad");
    }

    public static TradesManException tradesManNotFound(){
        return new TradesManException(9, TradesManExceptionTags.NOT_FOUND, "Trades Man not found");
    }

    public static TradesManException failedInsertion(){
        return new TradesManException(10, TradesManExceptionTags.FAILED_INSERTION, "Error in inserting trades man");
    }

    public static TradesManException failedDeletion(){
        return new TradesManException(11, TradesManExceptionTags.FAILED_DELETION, "Error in deletion trades man");
    }

    public static TradesManException noTradesMan(){
        return new TradesManException(12, TradesManExceptionTags.NO_TRADES_MAN, "There is no trades man");
    }
}
