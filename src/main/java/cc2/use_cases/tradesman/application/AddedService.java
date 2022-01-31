package cc2.use_cases.tradesman.application;

import cc2.use_cases.tradesman.domain.events.AddedTradesManEvent;
import cc2.use_cases.tradesman.domain.TradesMan;
import cc2.use_cases.tradesman.domain.events.Event;
import cc2.use_cases.tradesman.domain.events.EventBus;

public class AddedService {
    private final EventBus<Event> eventBus;

    public AddedService(EventBus<Event> eventBus) {
        this.eventBus = eventBus;
    }

    public void register(TradesMan tradesMan) {
        System.out.println("Ajout de :  " + tradesMan);
        eventBus.send(AddedTradesManEvent.withUser(tradesMan));
    }

}