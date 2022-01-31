package cc2.use_cases.tradesman.domain.events;

import cc2.use_cases.tradesman.domain.TradesMan;

import java.util.Date;

public class VerificationTradesManEvent {
    private final EventId eventId;
    private final Date createdAt;
    private final TradesMan tradesMan;

    public VerificationTradesManEvent(EventId eventId, Date createdAt, TradesMan tradesMan) {
        this.eventId = eventId;
        this.createdAt = createdAt;
        this.tradesMan = tradesMan;
    }

    public static VerificationTradesManEvent withUser(TradesMan tradesMan){
        return new VerificationTradesManEvent(EventId.create(), new Date(), tradesMan);
    }

}
