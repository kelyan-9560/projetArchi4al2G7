package cc2.use_cases.tradesman.application;

import cc2.use_cases.tradesman.application.events.AddedTradesManEvent;
import cc2.use_cases.tradesman.domain.TradesMan;
import cc2.kernel.Event;
import cc2.kernel.EventBus;

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