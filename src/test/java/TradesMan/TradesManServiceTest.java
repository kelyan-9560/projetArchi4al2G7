package TradesMan;

import cc2.use_cases.tradesman.domain.events.AddedUserEventSubscription;
import cc2.use_cases.tradesman.domain.Payment;
import cc2.use_cases.tradesman.domain.SendMailToUser;
import cc2.use_cases.tradesman.application.TradesManDTO;
import cc2.use_cases.tradesman.application.TradesManService;
import cc2.use_cases.tradesman.domain.*;
import cc2.use_cases.tradesman.domain.events.AddedTradesManEvent;
import cc2.use_cases.tradesman.domain.events.DefaultEventBus;
import cc2.use_cases.tradesman.domain.exception.TradesManException;
import cc2.use_cases.tradesman.infrastructure.InMemoryTradesManRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class TradesManServiceTest {

    Map<Class<AddedTradesManEvent>, List<AddedUserEventSubscription>> subscriptionMap = Collections.singletonMap(AddedTradesManEvent.class,
            Collections.singletonList(new AddedUserEventSubscription(new SendMailToUser(), new Payment())));

    DefaultEventBus eventBus = new DefaultEventBus(subscriptionMap);

    @Test
    void createUser(){

        TradesManRepository tradesManRepository = new InMemoryTradesManRepository();

        TradesManService tradesManService = new TradesManService(tradesManRepository, eventBus);

        final Email email = new Email("kelyan.bervin@gmail.com");
        final CreditCard creditCard = new CreditCard("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = new Location("Ile-de-France", "Ermont");
        final TradesManDTO tradesManDTO = new TradesManDTO("Kélyan", "BERVIN", email,
                creditCard, "Dev", "Java", 0.5, location, "Bachelor");


        //création du TradesMan avec le DTO
        TradesManId idTradesManCreated = tradesManService.create(tradesManDTO); //la création return un id

        //ajout de l'id
        TradesMan tradesMan = TradesMan.of(idTradesManCreated,"Kélyan", "BERVIN", email,
        creditCard, "Dev", "Java", 0.5, location, "Bachelor");

        TradesMan tradesManCreated = tradesManRepository.getById(idTradesManCreated);

        assertEquals(tradesMan, tradesManCreated);

    }

    @Test
    void getUserById(){

        TradesManRepository inMemoryTradesManRepository = new InMemoryTradesManRepository();

        TradesManService tradesManService = new TradesManService(inMemoryTradesManRepository, eventBus);

        final Email email = Email.of("kelyan.bervin@gmail.com");
        final TradesManId tradesManId = tradesManService.nextId();
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");

        final TradesMan tradesMan = TradesMan.of(tradesManId,"Kélyan", "BERVIN", email,
                creditCard, "Dev", "Java", 0.5, location, "Bachelor");


        inMemoryTradesManRepository.add(tradesMan);

        assertEquals(tradesMan, tradesManService.getById(tradesManId));
    }

    @Test
    void getAllUsers(){
        TradesManRepository inMemoryTradesManRepository = new InMemoryTradesManRepository();
        TradesManService tradesManService = new TradesManService(inMemoryTradesManRepository, eventBus);

        //TradesMan1
        final TradesManId tradesManId1 = new TradesManId("1");
        final Email email = Email.of("kelyan.bervin@gmail.com");
        final CreditCard creditCard1 = CreditCard.of("1234567634", "BERVIN1", LocalDateTime.now());
        final Location location1 = Location.of("Ile-de-France", "Ermont1");
        final TradesMan tradesMan1 = TradesMan.of(tradesManId1, "Kélyan1", "BERVIN1", email,
                creditCard1, "Dev1", "Java1", 0.5, location1, "Bachelor1");

        //TradesMan2
        final TradesManId tradesManId2 = new TradesManId("2");
        final Email email2 = Email.of("kelyan.bervin2@gmail.com");
        final CreditCard creditCard2 = CreditCard.of("1234567634", "BERVIN2", LocalDateTime.now());
        final Location location2 = Location.of("Ile-de-France", "Ermon2");
        final TradesMan tradesMan2 = TradesMan.of(tradesManId2,"Kélyan2", "BERVIN2", email2,
                creditCard2, "Dev2", "Java2", 12.0, location2, "Bachelor2");

        List<TradesMan> tradesManList = new ArrayList<>();
        tradesManList.add(tradesMan1);
        tradesManList.add(tradesMan2);

        inMemoryTradesManRepository.add(tradesMan1);
        inMemoryTradesManRepository.add(tradesMan2);

        assertEquals(tradesManService.getAll(), tradesManList);
    }

    @Test
    void deleteUser(){
        TradesManRepository inMemoryTradesManRepository = new InMemoryTradesManRepository();
        TradesManService tradesManService = new TradesManService(inMemoryTradesManRepository, eventBus);

        final TradesManId tradesManId = tradesManService.nextId();
        final Email email = Email.of("kelyan.bervin@gmail.com");
        final CreditCard creditCard = CreditCard.of("1234567634", "BERVIN", LocalDateTime.now());
        final Location location = Location.of("Ile-de-France", "Ermont");

        final TradesMan tradesMan = TradesMan.of(tradesManId,"Kélyan", "BERVIN", email,
                creditCard, "Dev", "Java", 0.5, location, "Bachelor");

        inMemoryTradesManRepository.add(tradesMan);
        inMemoryTradesManRepository.delete(tradesManId);

        try{
            tradesManService.getById(tradesManId);
            fail("Should throw exception");
        } catch (TradesManException tradesManException){
            assert (true);
        }

    }
}
