package cc2.use_cases.match_tradesman_project.infrastructure;

import cc2.use_cases.match_tradesman_project.domain.MatchProjectTradesman;
import cc2.use_cases.match_tradesman_project.domain.MatchProjectTradesmanId;
import cc2.use_cases.match_tradesman_project.domain.MatchProjectTradesmanRepository;
import cc2.use_cases.match_tradesman_project.domain.exception.MatchProjectTradesmanException;
import cc2.use_cases.tradesman.domain.TradesMan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class InMemoryMatchProjectTradesmanRepository implements MatchProjectTradesmanRepository {

    private final Map<MatchProjectTradesmanId, MatchProjectTradesman> data = new HashMap<>();

    @Override
    public void create(MatchProjectTradesman matchProjectTradesman) {
        final MatchProjectTradesman exists = getById(matchProjectTradesman.getMatchProjectTradesmanId());
        if(exists != null) return;

        MatchProjectTradesman match = matchProjectTradesman;
        match.assignBestFitTradesMan();
        try {
            data.put(matchProjectTradesman.getMatchProjectTradesmanId() ,match);
        } catch (Exception e){
            throw MatchProjectTradesmanException.failedInsertion();
        }
    }

    @Override
    public MatchProjectTradesman getById(MatchProjectTradesmanId matchProjectTradesmanId) {
        return null;
    }

    @Override
    public List<MatchProjectTradesman> getAll() {
        return null;
    }

    @Override
    public void delete(MatchProjectTradesmanId matchProjectTradesmanId) {
        try {
            data.remove(matchProjectTradesmanId);
        } catch (Exception e) {
            throw MatchProjectTradesmanException.failedDeletion();
        }
    }

    @Override
    public List<MatchProjectTradesman> addMatchProjectTradesman(MatchProjectTradesmanId matchProjectTradesmanId, MatchProjectTradesman matchProjectTradesman) {
        return null;
    }

    @Override
    public TradesMan getBestFitTradesMan(MatchProjectTradesman matchProjectTradesman) {
        return matchProjectTradesman.getBestFitTradesMan();
    }
}
