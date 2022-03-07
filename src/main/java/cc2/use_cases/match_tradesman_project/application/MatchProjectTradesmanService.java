package cc2.use_cases.match_tradesman_project.application;

import cc2.kernel.MatchProjectTradesmanRequest;
import cc2.use_cases.match_tradesman_project.domain.MatchProjectTradesman;
import cc2.use_cases.match_tradesman_project.domain.MatchProjectTradesmanId;
import cc2.use_cases.match_tradesman_project.domain.MatchProjectTradesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MatchProjectTradesmanService {

    private final MatchProjectTradesmanRepository matchProjectTradesmanRepository;

    @Autowired
    public MatchProjectTradesmanService(MatchProjectTradesmanRepository matchProjectTradesmanRepository) {
        this.matchProjectTradesmanRepository = matchProjectTradesmanRepository;
    }

    public MatchProjectTradesmanId create(MatchProjectTradesmanRequest matchProjectTradesmanRequest){

        final MatchProjectTradesmanId matchProjectTradesmanId = matchProjectTradesmanRepository.nextId();
        final MatchProjectTradesman matchProjectTradesman = MatchProjectTradesman.of(matchProjectTradesmanId, matchProjectTradesmanRequest.project, matchProjectTradesmanRequest.tradesManList);
        matchProjectTradesman.assignBestFitTradesMan();
        matchProjectTradesmanRepository.create(matchProjectTradesman);
        return matchProjectTradesmanId;
    }

    public MatchProjectTradesman getById(MatchProjectTradesmanId matchProjectTradesmanId){
        return matchProjectTradesmanRepository.getById(matchProjectTradesmanId);
    }

    public List<MatchProjectTradesman> getAll(){
        return matchProjectTradesmanRepository.getAll();
    }

    public void delete(MatchProjectTradesmanId matchProjectTradesmanId){
        matchProjectTradesmanRepository.delete(matchProjectTradesmanId);
    }

    /*
    public MatchProjectTradesman updateTradesmanList(List<TradesMan> tradesManListUpdated){
        return new MatchProjectTradesman(matchProjectTradesmanId, tradesManListUpdated)
    }
    */
}
