package TradesMan;

import cc2.use_cases.tradesman.domain.*;
import cc2.use_cases.tradesman.domain.exception.TradesManException;
import org.junit.jupiter.api.Test;
import cc2.use_cases.tradesman.application.TradesManVerificationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class TradesManVerificationServiceTest {

    @Test
    public void dailyTaxMoreThanZero(){

        List skills = new ArrayList();
        skills.add("Plomberie");
        skills.add("Mécanique");

        List diplomas = new ArrayList();
        diplomas.add(new Diploma(new DiplomaId("0"), "Bachelor", new Date()));

        final Double dailyTax = 0.1;

        TradesManVerificationService tradesManVerificationService = new TradesManVerificationService(null);

        final TradesManId tradesManId = TradesManId.of("1");
        final Email email = Email.of("kelyan.bervin@gmail.com");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", "BERVIN", email,
                creditCard, "Dev", skills, dailyTax, location, diplomas);


        try{
            tradesManVerificationService.dailyTaxVerification(tradesMan);
        } catch (TradesManException tradesManException){
            fail("Should'nt throw exception");
            assert (false);
        }

    }

    @Test
    public void dailyTaxLessThanZero(){

        List skills = new ArrayList();
        skills.add("Plomberie");
        skills.add("Mécanique");

        List diplomas = new ArrayList();
        diplomas.add(new Diploma(new DiplomaId("0"), "Bachelor", new Date()));

        final Double dailyTax = -1.1;

        TradesManVerificationService tradesManVerificationService = new TradesManVerificationService(null);

        final TradesManId tradesManId = TradesManId.of("1");
        final Email email = Email.of("kelyan.bervin@gmail.com");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", "BERVIN", email,
                creditCard, "Dev", skills, dailyTax, location, diplomas);


        try{
            tradesManVerificationService.dailyTaxVerification(tradesMan);
            fail("Should throw exception");
        } catch (TradesManException tradesManException){
            assert (true);
        }


    }

    @Test
    public void dailyTaxEqualToZero(){

        List skills = new ArrayList();
        skills.add("Plomberie");
        skills.add("Mécanique");

        List diplomas = new ArrayList();
        diplomas.add(new Diploma(new DiplomaId("0"), "Bachelor", new Date()));

        final Double dailyTax = 0.0;

        TradesManVerificationService tradesManVerificationService = new TradesManVerificationService(null);

        final TradesManId tradesManId = TradesManId.of("1");
        final Email email = Email.of("kelyan.bervin@gmail.com");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", "BERVIN", email,
                creditCard, "Dev", skills, dailyTax, location, diplomas);

        try{
            tradesManVerificationService.dailyTaxVerification(tradesMan);
            fail("Should throw exception");
        } catch (TradesManException tradesManException){
            assert (true);
        }


    }

    @Test
    public void mailAddressValid(){

        List skills = new ArrayList();
        skills.add("Plomberie");
        skills.add("Mécanique");

        List diplomas = new ArrayList();
        diplomas.add(new Diploma(new DiplomaId("0"), "Bachelor", new Date()));

        final Email email = new Email("kelyan.bervin@gmail.com");

        TradesManVerificationService tradesManVerificationService = new TradesManVerificationService(null);

        final TradesManId tradesManId = TradesManId.of("1");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", "BERVIN", email,
                creditCard, "Dev", skills, 0.1, location, diplomas);


        try{
            tradesManVerificationService.mailAddressVerification(tradesMan);
        } catch (TradesManException tradesManException){
            fail("Should'nt throw exception");
            assert (false);
        }

    }

    @Test
    public void mailAddressNotValid(){

        List skills = new ArrayList();
        skills.add("Plomberie");
        skills.add("Mécanique");

        List diplomas = new ArrayList();
        diplomas.add(new Diploma(new DiplomaId("0"), "Bachelor", new Date()));

        final Email email = new Email("kelyan.bervingmail.com");

        TradesManVerificationService tradesManVerificationService = new TradesManVerificationService(null);

        final TradesManId tradesManId = TradesManId.of("1");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");
        final TradesMan tradesMan = TradesMan.of(tradesManId, "Kélyan", "BERVIN", email,
                creditCard, "Dev", skills, 0.0, location, diplomas);

        try{
            tradesManVerificationService.mailAddressVerification(tradesMan);
            fail("Should throw exception");
        } catch (TradesManException tradesManException){
            assert (true);
        }

    }
}
