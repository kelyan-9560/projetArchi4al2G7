package TradesMan;

import cc2.use_cases.tradesman.application.CreditCardVerificationService;
import cc2.use_cases.tradesman.domain.*;
import cc2.use_cases.tradesman.domain.exception.CreditCardException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class CreditCardVerificationServiceTest {

    @Test
    public void numberIsValid(){

        final String creditCardNumber = "123456789009876543";

        CreditCardVerificationService creditCardVerificationService = new CreditCardVerificationService(null);
        CreditCard creditCard = CreditCard.of(creditCardNumber, "Jean", LocalDateTime.now());

        try{
            creditCardVerificationService.numberIsValid(creditCard);
        } catch (CreditCardException creditCardException){
            fail("Should'nt throw exception");
            assert (false);
        }
    }


    @Test
    public void numberNotValid(){

        final String creditCardNumber = "1234567890";

        CreditCardVerificationService creditCardVerificationService = new CreditCardVerificationService(null);
        CreditCard creditCard = CreditCard.of(creditCardNumber, "Jean", LocalDateTime.now());

        try{
            creditCardVerificationService.numberIsValid(creditCard);
            fail("Should throw exception");
        } catch (CreditCardException creditCardException){
            assert (true);
        }

    }

    @Test
    public void expirationDateValid(){

        final LocalDateTime creditCardExpirationDate = LocalDateTime.now();

        CreditCardVerificationService creditCardVerificationService = new CreditCardVerificationService(null);
        CreditCard creditCard = CreditCard.of("123456789009876543", "Jean", creditCardExpirationDate);

        try{
            creditCardVerificationService.expirationDateIsValid(creditCard);
        } catch (CreditCardException creditCardException){
            fail("Should'nt throw exception");
            assert (false);
        }

    }

    @Test
    public void expirationDateNotValid(){
        CreditCardVerificationService creditCardVerificationService = new CreditCardVerificationService(null);

        final LocalDateTime creditCardExpirationDate = LocalDateTime.of(2010, 12, 15,0,0,0);

        CreditCard creditCard = CreditCard.of("123456789009876543", "Jean", creditCardExpirationDate);

        try{
            creditCardVerificationService.expirationDateIsValid(creditCard);
            fail("Should throw exception");
        } catch (CreditCardException creditCardException){
            assert (true);
        }
    }


    @Test
    public void creditCardOwnerNameSameAsUserLastname(){

        List skills = new ArrayList();
        skills.add("Plomberie");
        skills.add("Mécanique");

        List diplomas = new ArrayList();
        diplomas.add(new Diploma(new DiplomaId("0"), "Bachelor", new Date()));

        CreditCardVerificationService creditCardVerificationService = new CreditCardVerificationService(null);

        final String creditCardOwnerName = "MESSI";
        final String tradesManLastname = "MESSI";

        final TradesManId tradesManId = TradesManId.of("1");
        final Email email = Email.of("kelyan.bervin@gmail.com");
        final CreditCard creditCard = CreditCard.of("123456789009876543", creditCardOwnerName, LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", tradesManLastname, email,
                creditCard, "Dev", skills, 0.1, location, diplomas);


        try{
            creditCardVerificationService.ownerNameIsSameAsUserLastname(creditCard, tradesMan);
        } catch (CreditCardException creditCardException){
            fail("Should'nt throw exception");
            assert (false);
        }

    }

    @Test
    public void creditCardOwnerNameDifferentAsUserLastname(){

        List skills = new ArrayList();
        skills.add("Plomberie");
        skills.add("Mécanique");

        List diplomas = new ArrayList();
        diplomas.add(new Diploma(new DiplomaId("0"), "Bachelor", new Date()));

        CreditCardVerificationService creditCardVerificationService = new CreditCardVerificationService(null);

        final String creditCardOwnerName = "MESSI";
        final String tradesManLastname = "RONALDO";

        final TradesManId tradesManId = TradesManId.of("1");
        final Email email = Email.of("kelyan.bervin@gmail.com");
        final CreditCard creditCard = CreditCard.of("123456789009876543", creditCardOwnerName, LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", tradesManLastname, email,
                creditCard, "Dev", skills, 0.1, location, diplomas);


        try{
            creditCardVerificationService.ownerNameIsSameAsUserLastname(creditCard, tradesMan);
            fail("Should throw exception");
        } catch (CreditCardException creditCardException){
            assert (true);
        }

    }

}
