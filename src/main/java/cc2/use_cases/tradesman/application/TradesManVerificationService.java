package cc2.use_cases.tradesman.application;

import cc2.use_cases.tradesman.domain.exception.TradesManException;
import cc2.use_cases.tradesman.domain.TradesMan;
import cc2.use_cases.tradesman.domain.events.VerificationTradesManEvent;
import cc2.use_cases.tradesman.domain.events.Event;
import cc2.use_cases.tradesman.domain.events.EventBus;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TradesManVerificationService {

    private final EventBus<Event> eventBus;

    public TradesManVerificationService(EventBus<Event> eventBus) {
        this.eventBus = eventBus;
    }


    public void dailyTaxVerification(TradesMan tradesMan) throws TradesManException{
        if(tradesMan.getDailyTax() <= 0.0){
            throw TradesManException.withDailyTax(tradesMan.getDailyTax());
        }
    }

    public void mailAddressVerification(TradesMan tradesMan) throws TradesManException {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tradesMan.getEmail().toString());

        if(!matcher.matches()){
            throw TradesManException.withEmail(tradesMan.getEmail().toString());
        }
    }

    public void userVerification(TradesMan tradesMan) {
        mailAddressVerification(tradesMan);
        dailyTaxVerification(tradesMan);

        eventBus.tradesManVerificationSubscriber(VerificationTradesManEvent.withUser(tradesMan));
    }

}
