package cc2.kernel;

import cc2.use_cases.tradesman.application.events.EventId;

import java.util.Date;

public interface Event {
    EventId getId();
    Date getDate();
}
