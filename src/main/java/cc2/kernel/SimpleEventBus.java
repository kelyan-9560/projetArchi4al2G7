package cc2.kernel;

import cc2.use_cases.tradesman.application.events.VerificationCreditCardEvent;
import cc2.use_cases.tradesman.application.events.VerificationTradesManEvent;

import java.util.List;

public class SimpleEventBus<E extends Event> implements EventBus<E>{
    @Override
    public void send(Event event) {

    }

    @Override
    public void registerSubscriber(Class classE, List list) {

    }

    @Override
    public void tradesManVerificationSubscriber(VerificationTradesManEvent verificationTradesManEvent) {

    }

    @Override
    public void creditCardVerificationSubscriber(VerificationCreditCardEvent verificationCreditCardEvent) {

    }
}
