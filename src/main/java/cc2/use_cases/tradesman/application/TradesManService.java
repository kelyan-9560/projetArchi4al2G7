package cc2.use_cases.tradesman.application;

import cc2.use_cases.tradesman.domain.TradesMan;
import cc2.use_cases.tradesman.domain.TradesManId;
import cc2.use_cases.tradesman.domain.TradesManRepository;
import cc2.use_cases.tradesman.domain.events.AddedTradesManEvent;
import cc2.use_cases.tradesman.domain.events.Event;
import cc2.use_cases.tradesman.domain.events.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TradesManService {

    private final TradesManRepository tradesManRepository;
    private final EventBus<Event> eventBus;

    @Autowired
    public TradesManService(TradesManRepository tradesManRepository, EventBus<Event> eventBus) {
        this.tradesManRepository = tradesManRepository;
        this.eventBus = eventBus;
    }


    public TradesManId nextId(){
        return tradesManRepository.nextId();
    }


    public TradesManId create(TradesManDTO tradesManDTO){
        final TradesManId tradesManId = tradesManRepository.nextId();
        final TradesMan tradesMan = TradesMan.of(tradesManId, tradesManDTO.firstname, tradesManDTO.lastname,
                                                    tradesManDTO.email,tradesManDTO.creditCard, tradesManDTO.job,
                                                    tradesManDTO.skill, tradesManDTO.dailyTax, tradesManDTO.location,
                                                    tradesManDTO.diplomas);
        tradesManRepository.add(tradesMan);
        eventBus.send(AddedTradesManEvent.withUser(tradesMan));
        return tradesManId;
    }


    public TradesMan getById(TradesManId tradesManId){
        return tradesManRepository.getById(tradesManId);
    }


    public List<TradesMan> getAll(){
        return tradesManRepository.getAll();
    }


    public void delete(TradesManId tradesManId){
        tradesManRepository.delete(tradesManId);
    }

}
