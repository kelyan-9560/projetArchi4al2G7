package cc2;

import cc2.kernel.*;
import cc2.use_cases.contractor.application.ContractorService;
import cc2.use_cases.contractor.domain.ContractorRepository;
import cc2.use_cases.contractor.exposition.ContractorController;
import cc2.use_cases.contractor.infrastructure.InMemoryContractorRepository;
import cc2.use_cases.tradesman.application.events.VerificationCreditCardEvent;
import cc2.use_cases.tradesman.application.events.VerificationTradesManEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
        /*
        var subscriptionMap = Collections.singletonMap(AddedTradesManEvent.class,
                        Collections.singletonList(new AddedUserEventSubscription(new SendMailToUser(), new Payment())));

        var eventBus = new DefaultEventBus(subscriptionMap);


        CreditCardVerificationService creditCardVerificationService = new CreditCardVerificationService(eventBus);
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

        creditCardVerificationService.creditCardVerification(creditCard, tradesMan);


        userVerificationService.userVerification(tradesMan);

        addedService.register(tradesMan);
         */

        CommandBus commandBus = new CommandBus() {
            @Override
            public <C extends Command, R> R send(C command) {
                return null;
            }
        };
        QueryBus queryBus = new QueryBus() {
            @Override
            public <Q extends Query, R> R send(Q query) {
                return null;
            }
        };

        ContractorRepository contractorRepository = new InMemoryContractorRepository();
        EventBus<Event> eventBus = new EventBus<Event>() {
            @Override
            public void send(Event event) {

            }

            @Override
            public void registerSubscriber(Class<Event> classE, List<Subscriber<Event>> subscribers) {

            }

            @Override
            public void tradesManVerificationSubscriber(VerificationTradesManEvent verificationTradesManEvent) {

            }

            @Override
            public void creditCardVerificationSubscriber(VerificationCreditCardEvent verificationCreditCardEvent) {

            }
        };
        ContractorController contractorController = new ContractorController(commandBus, queryBus, new ContractorService(contractorRepository, eventBus));
        //RoutingContext routingContext = new RoutingContext();
        System.out.println("App...");
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ApiVerticle(contractorController));

    }
}