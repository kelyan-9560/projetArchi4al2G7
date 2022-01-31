package cc2.use_cases.tradesman.domain.events;

import cc2.use_cases.tradesman.domain.TradesMan;

import java.util.Date;

public class AddedTradesManEvent implements Event {
    private final EventId eventId;
    private final Date eventDate;
    private final TradesMan tradesMan;

    public AddedTradesManEvent(EventId eventId, Date eventDate, TradesMan tradesMan) {
        this.eventId = eventId;
        this.eventDate = eventDate;
        this.tradesMan = tradesMan;
    }

    public static AddedTradesManEvent withUser(TradesMan tradesMan){
        return new AddedTradesManEvent(EventId.create(), new Date(), tradesMan);
    }


    @Override
    public EventId getId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    public TradesMan getTradesMan() {
        return tradesMan;
    }
}