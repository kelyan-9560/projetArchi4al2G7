package cc2.kernel.exception;

public class PasswordException extends RuntimeException {
    public PasswordException(int errorCode, PasswordExceptionTags creditCardsExceptionsTags, String detail) {}

    public static PasswordException invalidPassword(){
        return new PasswordException(1, PasswordExceptionTags.INVALID_PASSWORD,
                " A password must contain 8 to 15 characters, 1 or more capital letter, 1 or more small letter, 1 or more number and at least one of these special characters: $ @ % * + - _ !.");
    }

    public static PasswordException badPassword(){
        return new PasswordException(2, PasswordExceptionTags.BAD_PASSWORD," Password doesn't match.");
    }

    public static PasswordException samePassword(){
        return new PasswordException(3, PasswordExceptionTags.SAME_PASSWORD, " Password cannot be the same as the precedent.");
    }
}
