package cc2.use_cases.tradesman.domain.events;

import cc2.use_cases.tradesman.domain.CreditCard;
import cc2.use_cases.tradesman.domain.TradesMan;

import java.util.Date;

public class VerificationCreditCardEvent {
    private final EventId eventId;
    private final Date createdAt;
    private final TradesMan tradesMan;
    private final CreditCard creditCard;


    public VerificationCreditCardEvent(EventId eventId, Date createdAt, TradesMan tradesMan, CreditCard creditCard) {
        this.eventId = eventId;
        this.createdAt = createdAt;
        this.tradesMan = tradesMan;
        this.creditCard = creditCard;
    }

    public static VerificationCreditCardEvent withData(TradesMan tradesMan, CreditCard creditCard){
        return new VerificationCreditCardEvent(EventId.create(), new Date(), tradesMan, creditCard);
    }
}
