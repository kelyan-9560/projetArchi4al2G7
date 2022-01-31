package TradesMan;

import cc2.use_cases.tradesman.domain.*;
import cc2.use_cases.tradesman.domain.exception.TradesManException;
import org.junit.jupiter.api.Test;
import cc2.use_cases.tradesman.application.TradesManVerificationService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;

public class TradesManVerificationServiceTest {

    @Test
    public void dailyTaxMoreThanZero(){

        final Double dailyTax = 0.1;

        TradesManVerificationService tradesManVerificationService = new TradesManVerificationService(null);

        final TradesManId tradesManId = TradesManId.of("1");
        final Email email = Email.of("kelyan.bervin@gmail.com");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", "BERVIN", email,
                creditCard, "Dev", "Java", dailyTax, location, "Bachelor");


        try{
            tradesManVerificationService.dailyTaxVerification(tradesMan);
        } catch (TradesManException tradesManException){
            fail("Should'nt throw exception");
            assert (false);
        }

    }

    @Test
    public void dailyTaxLessThanZero(){

        final Double dailyTax = -1.1;

        TradesManVerificationService tradesManVerificationService = new TradesManVerificationService(null);

        final TradesManId tradesManId = TradesManId.of("1");
        final Email email = Email.of("kelyan.bervin@gmail.com");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", "BERVIN", email,
                creditCard, "Dev", "Java", dailyTax, location, "Bachelor");


        try{
            tradesManVerificationService.dailyTaxVerification(tradesMan);
            fail("Should throw exception");
        } catch (TradesManException tradesManException){
            assert (true);
        }


    }

    @Test
    public void dailyTaxEqualToZero(){

        final Double dailyTax = 0.0;

        TradesManVerificationService tradesManVerificationService = new TradesManVerificationService(null);

        final TradesManId tradesManId = TradesManId.of("1");
        final Email email = Email.of("kelyan.bervin@gmail.com");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", "BERVIN", email,
                creditCard, "Dev", "Java", dailyTax, location, "Bachelor");

        try{
            tradesManVerificationService.dailyTaxVerification(tradesMan);
            fail("Should throw exception");
        } catch (TradesManException tradesManException){
            assert (true);
        }


    }

    @Test
    public void mailAddressValid(){

        final Email email = new Email("kelyan.bervin@gmail.com");

        TradesManVerificationService tradesManVerificationService = new TradesManVerificationService(null);

        final TradesManId tradesManId = TradesManId.of("1");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", "BERVIN", email,
                creditCard, "Dev", "Java", 0.1, location, "Bachelor");


        try{
            tradesManVerificationService.mailAddressVerification(tradesMan);
        } catch (TradesManException tradesManException){
            fail("Should'nt throw exception");
            assert (false);
        }

    }

    @Test
    public void mailAddressNotValid(){

        final Email email = new Email("kelyan.bervingmail.com");

        TradesManVerificationService tradesManVerificationService = new TradesManVerificationService(null);

        final TradesManId tradesManId = TradesManId.of("1");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", "BERVIN", email,
                creditCard, "Dev", "Java", 0.0, location, "Bachelor");

        try{
            tradesManVerificationService.mailAddressVerification(tradesMan);
            fail("Should throw exception");
        } catch (TradesManException tradesManException){
            assert (true);
        }

    }
}
