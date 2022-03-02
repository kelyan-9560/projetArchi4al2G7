package cc2.use_cases.contractor.exposition;

import cc2.kernel.*;
import cc2.use_cases.contractor.application.ContractorDTO;
import cc2.use_cases.contractor.application.ContractorService;
import cc2.use_cases.contractor.domain.Contractor;
import cc2.use_cases.contractor.domain.ContractorId;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.time.LocalDateTime;
import java.util.List;

public class ContractorController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private final ContractorService contractorService;


    public ContractorController(CommandBus commandBus, QueryBus queryBus,ContractorService contractorService) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.contractorService = contractorService;
    }

    public Router getSubRouter(final Vertx vertx) {
        final Router subRouter = Router.router(vertx);

        // Body handler
        subRouter.route("/*").handler(BodyHandler.create());

        // Routes
        subRouter.post("/").handler(this::createContractor);
        subRouter.get("/:id").handler(this::getContractorById);
        subRouter.get("/").handler(this::getAllContractor);
        subRouter.delete("/:id").handler(this::deleteContractorById);


        return subRouter;
    }


    // TODO : Vérifier les champs (service de vérification etc...)


    private void createContractor(final RoutingContext routingContext) {
        System.out.println("Dans le create...");

        final JsonObject body = routingContext.getBodyAsJson();
        final String passwordParam = body.getString("password");
        final String creditCardNumber = body.getString("creditCardNumber");
        final String ownerName = body.getString("ownerName");
        final String emailParam = body.getString("email");
        final String firstname = body.getString("firstname");
        final String lastname = body.getString("lastname");


        if (passwordParam == null || creditCardNumber == null || ownerName == null || emailParam == null
                || firstname == null || lastname == null) {

            routingContext.response().setStatusCode(403).end("An argument is missing");
            return;
        }

        final Password password = new Password(passwordParam);
        final CreditCard creditCard = new CreditCard(creditCardNumber,ownerName, LocalDateTime.now());
        final Email email = new Email(emailParam);
        final ContractorDTO contractorDTO = new ContractorDTO(firstname, lastname, password, creditCard, email);

        final ContractorId contractorId = contractorService.create(contractorDTO);

        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("contractor", contractorId);
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(jsonResponse));

    }

    private void getContractorById(RoutingContext routingContext) {
        System.out.println("Dans le getContractorById...");

        final String id = routingContext.request().getParam("id");
        if(id == null) routingContext.response().setStatusCode(403).end("Id is missing");


        final ContractorId contractorId = ContractorId.of(id);

        final Contractor contractor = contractorService.getById(contractorId);

        if (contractor == null) {
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "No contractor can be found for the specified id:" + id);
            errorJsonResponse.put("id", id);

            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
            return;
        }
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(contractor));


    }

    private void getAllContractor(final RoutingContext routingContext) {
        System.out.println("Dans getAllDogs...");

        final List<Contractor> contractorList = contractorService.getAll();

        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("contractorList", contractorList);
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(jsonResponse));

    }

    private void deleteContractorById(RoutingContext routingContext) {
        System.out.println("Dans le deleteContractorById...");

        final String id = routingContext.request().getParam("id");
        if(id == null) routingContext.response().setStatusCode(403).end("Id is missing");

        final ContractorId contractorId = ContractorId.of(id);

        final Contractor contractor = contractorService.getById(contractorId);

        if (contractor == null) {
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "No contractor can be found for the specified id:" + id);
            errorJsonResponse.put("id", id);

            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
            return;
        }

        contractorService.delete(contractorId);

        final JsonObject jsonResponse = new JsonObject();
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(jsonResponse), "Contractor Deleted");
    }



}
