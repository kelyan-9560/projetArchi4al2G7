package cc2.use_cases.tradesman.infrastructure;

import cc2.use_cases.tradesman.domain.exception.TradesManException;
import cc2.use_cases.tradesman.domain.TradesMan;
import cc2.use_cases.tradesman.domain.TradesManId;
import cc2.use_cases.tradesman.domain.TradesManRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTradesManRepository implements TradesManRepository {

    private final Map<TradesManId, TradesMan> data = new HashMap<>();


    @Override
    public void add(TradesMan tradesMan) {
        try {
            data.put(tradesMan.getTradesManId(), tradesMan);
        } catch (Exception e) {
            throw TradesManException.failedInsertion();
        }
    }

    @Override
    public TradesMan getById(TradesManId tradesManId) {
        final TradesMan tradesMan = data.get(tradesManId);
        if (tradesMan == null) {
            throw TradesManException.tradesManNotFound();
        }
        return tradesMan;
    }

    @Override
    public List<TradesMan> getAll() {
        if(data.isEmpty()){
            throw TradesManException.noTradesMan();
        }
        return List.copyOf(data.values());
    }

    @Override
    public void delete(TradesManId tradesManId) {
        try {
            data.remove(tradesManId);
        } catch (Exception e) {
            throw TradesManException.failedDeletion();
        }
    }
}
