package cc2.use_cases.contractor.exposition;

import cc2.kernel.*;
import cc2.use_cases.contractor.application.ContractorDTO;
import cc2.use_cases.contractor.application.ContractorService;
import cc2.use_cases.contractor.application.CreateContractor;
import cc2.use_cases.contractor.application.RetrieveContractors;
import cc2.use_cases.contractor.domain.Contractor;
import cc2.use_cases.contractor.domain.ContractorId;
import cc2.use_cases.tradesman.application.CreditCardValidator;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ContractorController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private final ContractorService contractorService;
    private final CreditCardValidator creditCardValidator;

    public ContractorController(CommandBus commandBus, QueryBus queryBus,ContractorService contractorService, CreditCardValidator creditCardValidator) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.contractorService = contractorService;
        this.creditCardValidator = creditCardValidator;
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
        final CreditCard creditCard = CreditCard.of(creditCardNumber,ownerName, LocalDateTime.now());
        final Email email = Email.of(emailParam);
        final ContractorDTO contractorDTO = new ContractorDTO(firstname, lastname, password, creditCard, email);


        final ContractorId contractorId = contractorService.create(contractorDTO);

        final CreateContractor createContractor = new CreateContractor(firstname, lastname, password, creditCard, email);
        commandBus.send(createContractor);

        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("contractor", contractorId);
        routingContext.response()
                .setStatusCode(201)
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
            errorJsonResponse.put("error", "No contractor can be found for the specified id : " + id);

            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
        }

        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(contractor.toString()));


    }

    private void getAllContractor(final RoutingContext routingContext) {
        System.out.println("Dans getAllContractor...");


        final List<Contractor> contractorList = contractorService.getAll();
        queryBus.send(new RetrieveContractors());

        if(contractorList.isEmpty()){
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "No contractor found");

            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
        }

        ContractorsResponse contractorsResponse = new ContractorsResponse(
                contractorList
                        .stream()
                        .map(contractor ->
                                new ContractorResponse(String.valueOf(contractor.getContractorId().getId()),
                                                                        contractor.getLastname(), contractor.getFirstname(),
                                                                        new Password(contractor.getPassword().getValue()),
                                                                        new CreditCard(contractor.getCreditCard().getNumber(), contractor.getCreditCard().getOwnerName(), contractor.getCreditCard().getExpirationDate()),
                                                                        new Email(contractor.getEmail().toString())))
                        .collect(Collectors.toList()));

        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("contractorList", contractorsResponse.contractorResponseList.toString());
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(jsonResponse));

    }

    private void deleteContractorById(RoutingContext routingContext) {
        System.out.println("Dans le deleteContractorById...");

        final String idPram = routingContext.request().getParam("id");
        if(idPram == null) {
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "Id param is missing");

            routingContext.response()
                    .setStatusCode(403)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
        }

        final ContractorId contractorId = ContractorId.of(idPram);

        final Contractor contractor = contractorService.getById(contractorId);

        if (contractor == null) {
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "No contractor can be found for the specified id:" + idPram);

            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
            return;
        }

        contractorService.delete(contractorId);

        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("Ok", "Contractor has been successfully deleted");

        routingContext.response()
                .setStatusCode(204)
                .putHeader("content-type", "application/json")
                .end(Json.encode(jsonResponse));
    }

}
