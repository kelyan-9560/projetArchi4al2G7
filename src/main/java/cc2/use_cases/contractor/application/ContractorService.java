package cc2.use_cases.contractor.application;

import cc2.use_cases.contractor.domain.Contractor;
import cc2.use_cases.contractor.domain.ContractorId;
import cc2.use_cases.contractor.domain.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cc2.use_cases.tradesman.domain.events.Event;
import cc2.use_cases.tradesman.domain.events.EventBus;

import java.util.List;

@Service
public class ContractorService {

    private final ContractorRepository contractorRepository;
    private final EventBus<Event> eventBus;

    @Autowired
    public ContractorService(ContractorRepository contractorRepository, EventBus<Event> eventBus) {
        this.contractorRepository = contractorRepository;
        this.eventBus = eventBus;
    }

    public ContractorId nextId(){
        return contractorRepository.nextId();
    }


    public ContractorId create(ContractorDTO contractorDTO){
        final ContractorId contractorId = contractorRepository.nextId();
        final Contractor contractor = Contractor.of(contractorId, contractorDTO.firstname, contractorDTO.lastname);

        contractorRepository.add(contractor);
        return contractorId;
    }

    public Contractor getById(ContractorId contractorId){
        return contractorRepository.getById(contractorId);
    }


    public List<Contractor> getAll(){
        return contractorRepository.getAll();
    }


    public void delete(ContractorId contractorId){
        contractorRepository.delete(contractorId);
    }
}
