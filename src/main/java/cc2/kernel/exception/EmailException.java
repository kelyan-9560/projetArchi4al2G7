package cc2.kernel.exception;

public class EmailException extends RuntimeException {
    public EmailException(int errorCode, EmailExceptionTags emailExceptionTags, String detail) {}

    public static EmailException notFound(){
        return new EmailException(1, EmailExceptionTags.NOT_FOUND, " Email not found.");
    }

    public static EmailException exist(){
        return new EmailException(2, EmailExceptionTags.ALREADY_EXIST, " This email as already exist");
    }

    public static EmailException invalid(){
        return new EmailException(3, EmailExceptionTags.INVALID_EMAIL, " Invalid Email");
    }
}
