package cc2.use_cases.tradesman.domain.events;

import java.util.UUID;

public class EventId {
    private  final String value;

    public EventId(String value) {
        this.value = value;
    }

    public static EventId create(){
        return new EventId(UUID.randomUUID().toString());
    }
}
