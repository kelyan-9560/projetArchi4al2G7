package cc2.use_cases.tradesman.domain.exception;

import java.time.LocalDateTime;

public final class CreditCardException extends RuntimeException {


    public CreditCardException(int errorCode, CreditCardsExceptionsTags creditCardsExceptionsTags, String detail) {
    }

    public static CreditCardException withNumber(String creditCardNumber){
        return new CreditCardException(1, CreditCardsExceptionsTags.BAD_NUMBER, creditCardNumber + " is a bad CreditCard number");
    }

    public static CreditCardException withDate(LocalDateTime expirationDate){
        return new CreditCardException(2, CreditCardsExceptionsTags.BAD_DATE, expirationDate + " is a bad CreditCard expiration date");
    }

    public static CreditCardException withOwnerName(String ownerName){
        return new CreditCardException(3, CreditCardsExceptionsTags.BAD_OWNER_NAME, ownerName + " is a bad CreditCard owner name");
    }
}
