package cc2.use_cases.tradesman.application;

import cc2.use_cases.tradesman.domain.events.VerificationCreditCardEvent;
import cc2.use_cases.tradesman.domain.exception.CreditCardException;
import cc2.use_cases.tradesman.domain.CreditCard;
import cc2.use_cases.tradesman.domain.TradesMan;
import cc2.use_cases.tradesman.domain.events.Event;
import cc2.use_cases.tradesman.domain.events.EventBus;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreditCardVerificationService {

    private final EventBus<Event> eventBus;

    public CreditCardVerificationService(EventBus<Event> eventBus) {
        this.eventBus = eventBus;
    }


    public void numberIsValid(CreditCard creditCard) throws CreditCardException {
        if(creditCard.getNumber().length() != 18){
            throw CreditCardException.withNumber(creditCard.getNumber());
        }
    }

    public void expirationDateIsValid(CreditCard creditCard) throws CreditCardException{
        if(creditCard.getExpirationDate().isBefore(LocalDateTime.now())){
            throw CreditCardException.withDate(creditCard.getExpirationDate());
        }
    }

    public void ownerNameIsSameAsUserLastname(CreditCard creditCard, TradesMan tradesMan) throws CreditCardException{
        if(!Objects.equals(creditCard.getOwnerName(), tradesMan.getLastname())){
            throw CreditCardException.withOwnerName(creditCard.getOwnerName());
        }
    }

    public void creditCardVerification(CreditCard creditCard, TradesMan tradesMan) {
        numberIsValid(creditCard);
        expirationDateIsValid(creditCard);
        ownerNameIsSameAsUserLastname(creditCard, tradesMan);

        eventBus.creditCardVerificationSubscriber(VerificationCreditCardEvent.withData(tradesMan, creditCard));
    }

}
