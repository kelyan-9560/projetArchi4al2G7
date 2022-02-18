package cc2;

import cc2.kernel.CreditCard;
import cc2.kernel.Email;
import cc2.kernel.Location;
import cc2.use_cases.tradesman.domain.*;
import cc2.use_cases.tradesman.application.events.AddedTradesManEvent;
import cc2.use_cases.tradesman.application.events.DefaultEventBus;
import cc2.use_cases.tradesman.application.events.AddedUserEventSubscription;
import cc2.kernel.Payment;
import cc2.use_cases.tradesman.domain.SendMailToUser;
import cc2.use_cases.tradesman.application.AddedService;
import cc2.use_cases.tradesman.application.CreditCardVerificationService;
import cc2.use_cases.tradesman.application.TradesManVerificationService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
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

    }
}