package cc2.use_cases.tradesman.application.events;

import cc2.kernel.Event;
import cc2.kernel.EventBus;
import cc2.kernel.Subscriber;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DefaultEventBus<E extends Event> implements EventBus<E> {

    private final Map<Class<E>, List<Subscriber<E>>> subscribers;

    public DefaultEventBus(Map<Class<E>, List<Subscriber<E>>> subscribers) {
        this.subscribers = subscribers;
    }


    @Override
    public void send(E event) {
        final List<Subscriber<E>> subscribers = this.subscribers.get(event.getClass());
        if (subscribers == null || subscribers.isEmpty()) {
            throw new IllegalStateException("No subscriber for " + event.getClass().getSimpleName());
        }
        subscribers.forEach(subscriber -> subscriber.accept(event));
    }

    @Override
    public void registerSubscriber(Class<E> classE, List<Subscriber<E>> givenSubscribers) {
        subscribers.putIfAbsent(classE, givenSubscribers);
    }

    @Override
    public void tradesManVerificationSubscriber(VerificationTradesManEvent verificationUserEvent) {

    }

    @Override
    public void creditCardVerificationSubscriber(VerificationCreditCardEvent verificationCreditCardEvent) {

    }
}
