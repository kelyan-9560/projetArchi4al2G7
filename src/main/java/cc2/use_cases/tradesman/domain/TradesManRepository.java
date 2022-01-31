package cc2.use_cases.tradesman.domain;

import java.util.List;
import java.util.UUID;

public interface TradesManRepository {

    void add(TradesMan tradesMan);

    TradesMan getById(TradesManId tradesManId);

    List<TradesMan> getAll();

    void delete(TradesManId tradesManId);

    default TradesManId nextId(){
        return TradesManId.fromUUID(UUID.randomUUID());
    }
}
