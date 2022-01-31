package cc2.web_REST.controllers.tradesman;

import cc2.use_cases.tradesman.application.TradesManDTO;
import cc2.use_cases.tradesman.application.TradesManService;
import cc2.use_cases.tradesman.domain.TradesManId;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TradesManController {

    private final TradesManService tradesManService;


    public TradesManController(TradesManService tradesManService) {
        this.tradesManService = tradesManService;
    }


    @PostMapping(path = "/tradesMan", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody TradesManDTO tradesManDTO){
        tradesManService.create(tradesManDTO);
    }

    @GetMapping(path = "/tradesMan", consumes = MediaType.APPLICATION_JSON_VALUE)
    void getById(@RequestParam TradesManId tradesManId){
        tradesManService.getById(tradesManId);
    }

    @GetMapping(path = "/tradesMans", consumes = MediaType.APPLICATION_JSON_VALUE)
    void getAll(){
        tradesManService.getAll();
    }

    @PostMapping(path = "/delete/tradesMan", consumes = MediaType.APPLICATION_JSON_VALUE)
    void delete(@RequestParam TradesManId tradesManId){
        tradesManService.delete(tradesManId);
    }


}
