package cc2.use_cases.tradesman.domain.events;

import java.util.List;

public interface EventBus<E extends Event> {
    void send(E event);

    void registerSubscriber(Class<E> classE, List<Subscriber<E>> subscribers);

    void tradesManVerificationSubscriber(VerificationTradesManEvent verificationTradesManEvent);
    void creditCardVerificationSubscriber(VerificationCreditCardEvent verificationCreditCardEvent);
}
