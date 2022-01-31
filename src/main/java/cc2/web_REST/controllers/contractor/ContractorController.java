package cc2.web_REST.controllers.contractor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import cc2.use_cases.contractor.application.ContractorDTO;
import cc2.use_cases.contractor.application.ContractorService;
import cc2.use_cases.contractor.domain.ContractorId;

@Controller
public class ContractorController {

    private final ContractorService contractorService;

    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @PostMapping(path = "/contractor", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody ContractorDTO contractorDTO){
        contractorService.create(contractorDTO);
    }

    @GetMapping(path = "/contractor", consumes = MediaType.APPLICATION_JSON_VALUE)
    void getById(@RequestParam ContractorId contractorId){
        contractorService.getById(contractorId);
    }

    @GetMapping(path = "/contractors", consumes = MediaType.APPLICATION_JSON_VALUE)
    void getAll(){
        contractorService.getAll();
    }

    @PostMapping(path = "/delete/contractor", consumes = MediaType.APPLICATION_JSON_VALUE)
    void delete(@RequestParam ContractorId contractorId){
        contractorService.delete(contractorId);
    }

}
