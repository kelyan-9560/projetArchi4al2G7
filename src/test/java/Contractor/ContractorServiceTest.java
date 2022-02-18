package Contractor;

import cc2.kernel.Password;
import cc2.kernel.CreditCard;
import java.time.LocalDateTime;

import cc2.kernel.Email;
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

        final Password password = new Password("Azerty$1234");
        final CreditCard creditCard = new CreditCard("1111 1111 1111 1111","Kélyan Bervin",LocalDateTime.now());
        final Email email = new Email("kbervin@myges.fr");
        final ContractorDTO contractorDTO = new ContractorDTO("Kélyan", "BERVIN", password, creditCard, email);


        ContractorId idContractorCreated = contractorService.create(contractorDTO);
        Contractor contractor = Contractor.of(idContractorCreated,"Kélyan", "BERVIN",password,creditCard,email);

        Contractor contractorCreated = contractorRepository.getById(idContractorCreated);

        assertEquals(contractor, contractorCreated);
    }

    @Test
    void getContractorById(){
        ContractorRepository inMemoryContractorRepository = new InMemoryContractorRepository();
        ContractorService contractorService = new ContractorService(inMemoryContractorRepository, null);

        final Password password = new Password("Azery$1234");
        final CreditCard creditCard = new CreditCard("1111 1111 1111 1111","Kélyan Bervin", LocalDateTime.now());
        final Email email = new Email("kbervin@myges.fr");
        final ContractorId contractorId = contractorService.nextId();
        final Contractor contractor = Contractor.of(contractorId,"Kélyan", "BERVIN",password,creditCard,email);


        inMemoryContractorRepository.add(contractor);

        assertEquals(contractor, contractorService.getById(contractorId));
    }

    @Test
    void getAllContractor(){
        ContractorRepository inMemoryContractorRepository = new InMemoryContractorRepository();
        ContractorService contractorService = new ContractorService(inMemoryContractorRepository, null);

        final Password password = new Password("Azery$1234");
        final CreditCard creditCard = new CreditCard("1111 1111 1111 1111","Kélyan Bervin", LocalDateTime.now());
        final Email email = new Email("kbervin@myges.fr");
        final ContractorId contractorId1 = new ContractorId("1");
        final Contractor contractor1 = Contractor.of(contractorId1, "Kélyan1", "BERVIN1",password,creditCard,email);

        final ContractorId contractorId2 = new ContractorId("2");
        final Contractor contractor2 = Contractor.of(contractorId2,"Kélyan2", "BERVIN2",password,creditCard,email);

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

        final Password password = new Password("Azery$1234");
        final CreditCard creditCard = new CreditCard("1111 1111 1111 1111","Kélyan Bervin", LocalDateTime.now());
        final Email email = new Email("kbervin@myges.fr");
        final ContractorId contractorId = new ContractorId("1");
        final Contractor contractor = Contractor.of(contractorId, "Kélyan", "BERVIN", password, creditCard, email);


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
