package Contractor;

import org.junit.jupiter.api.Test;
import cc2.use_cases.contractor.application.ContractorDTO;
import cc2.use_cases.contractor.application.ContractorService;
import cc2.use_cases.contractor.domain.Contractor;
import cc2.use_cases.contractor.domain.ContractorId;
import cc2.use_cases.contractor.domain.ContractorRepository;
import cc2.use_cases.contractor.domain.exception.ContractorException;
import cc2.use_cases.contractor.infrastructure.InMemoryContractorRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ContractorServiceTest {

    @Test
    void createContractor(){
        ContractorRepository contractorRepository = new InMemoryContractorRepository();
        ContractorService contractorService = new ContractorService(contractorRepository, null);

        final ContractorDTO contractorDTO = new ContractorDTO("Kélyan", "BERVIN");


        ContractorId idContractorCreated = contractorService.create(contractorDTO);
        Contractor contractor = Contractor.of(idContractorCreated,"Kélyan", "BERVIN");

        Contractor contractorCreated = contractorRepository.getById(idContractorCreated);

        assertEquals(contractor, contractorCreated);
    }

    @Test
    void getContractorById(){
        ContractorRepository inMemoryContractorRepository = new InMemoryContractorRepository();
        ContractorService contractorService = new ContractorService(inMemoryContractorRepository, null);

        final ContractorId contractorId = contractorService.nextId();
        final Contractor contractor = Contractor.of(contractorId,"Kélyan", "BERVIN");


        inMemoryContractorRepository.add(contractor);

        assertEquals(contractor, contractorService.getById(contractorId));
    }

    @Test
    void getAllContractor(){
        ContractorRepository inMemoryContractorRepository = new InMemoryContractorRepository();
        ContractorService contractorService = new ContractorService(inMemoryContractorRepository, null);

        final ContractorId contractorId1 = new ContractorId("1");
        final Contractor contractor1 = Contractor.of(contractorId1, "Kélyan1", "BERVIN1");

        final ContractorId contractorId2 = new ContractorId("2");
        final Contractor contractor2 = Contractor.of(contractorId2,"Kélyan2", "BERVIN2");

        List<Contractor> contractorList = new ArrayList<>();
        contractorList.add(contractor1);
        contractorList.add(contractor2);

        inMemoryContractorRepository.add(contractor1);
        inMemoryContractorRepository.add(contractor2);

        assertEquals(contractorService.getAll(), contractorList);
    }

    @Test
    void deleteContractor(){
        ContractorRepository inMemoryContractorRepository = new InMemoryContractorRepository();
        ContractorService contractorService = new ContractorService(inMemoryContractorRepository, null);

        final ContractorId contractorId = new ContractorId("1");
        final Contractor contractor = Contractor.of(contractorId, "Kélyan", "BERVIN");


        inMemoryContractorRepository.add(contractor);
        inMemoryContractorRepository.delete(contractorId);

        try{
            contractorService.getById(contractorId);
            fail("Should throw exception");
        } catch (ContractorException contractorException){
            assert (true);
        }
    }

}
