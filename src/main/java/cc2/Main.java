package cc2;

import cc2.kernel.*;
import cc2.use_cases.contractor.application.ContractorService;
import cc2.use_cases.contractor.domain.ContractorRepository;
import cc2.use_cases.contractor.exposition.ContractorController;
import cc2.use_cases.contractor.infrastructure.InMemoryContractorRepository;
import cc2.use_cases.tradesman.application.AddedService;
import cc2.use_cases.tradesman.application.CreditCardValidator;
import cc2.use_cases.tradesman.application.TradesManService;
import cc2.use_cases.tradesman.application.TradesManVerificationService;
import cc2.use_cases.tradesman.application.events.AddedTradesManEvent;
import cc2.use_cases.tradesman.application.events.AddedUserEventSubscription;
import cc2.use_cases.tradesman.application.events.DefaultEventBus;
import cc2.use_cases.tradesman.domain.*;
import cc2.use_cases.tradesman.exposition.TradesManController;
import cc2.use_cases.tradesman.infrastructure.InMemoryTradesManRepository;
import io.vertx.core.Vertx;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {

        var subscriptionMap = Collections.singletonMap(AddedTradesManEvent.class,
                        Collections.singletonList(new AddedUserEventSubscription(new SendMailToUser(), new Payment())));

        var eventBus = new DefaultEventBus(subscriptionMap);


        CreditCardValidator creditCardValidator = new CreditCardValidator(eventBus);
        TradesManVerificationService userVerificationService = new TradesManVerificationService(eventBus);
        AddedService addedService = new AddedService(eventBus);


        Email email = new Email("kelyan.bervin@gmail.com");
        CreditCard creditCard = CreditCard.of("458729053978354612", "BERVIN", LocalDateTime.of(2070, 12, 15,0,0,0));
        Location location = Location.of("ile de france", "Paris");
        TradesManId tradesManId = TradesManId.of("1");
        List<String> skills = List.of("Java", "TS");

        DiplomaId diplomaId2 = DiplomaId.of("1");
        Diploma master = new Diploma(diplomaId2, "master", new Date());
        List<Diploma> diplomas = List.of(master);

        TradesMan tradesMan = TradesMan.of(tradesManId,"Kélyan ", "BERVIN", email,
                creditCard, "Macon", skills, 2.3, location, diplomas);

        creditCardValidator.creditCardVerification(creditCard, tradesMan);


        userVerificationService.userVerification(tradesMan);

        addedService.register(tradesMan);


        CommandBus commandBus = new SimpleCommandBus();
        QueryBus queryBus = new SimpleQueryBus();
        ContractorRepository contractorRepository = new InMemoryContractorRepository();
        EventBus<Event> eventBus1 = new SimpleEventBus<>();
        TradesManRepository tradesManRepository = new InMemoryTradesManRepository();

        ContractorController contractorController = new ContractorController(commandBus, queryBus, new ContractorService(contractorRepository, eventBus1), creditCardValidator);
        TradesManController tradesManController = new TradesManController(commandBus, queryBus, new TradesManService(tradesManRepository, eventBus1));


        System.out.println("App...");
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ApiVerticle(contractorController, tradesManController));

    }
}