package cc2.use_cases.tradesman.exposition;

import cc2.kernel.*;
import cc2.use_cases.tradesman.application.*;
import cc2.use_cases.tradesman.domain.TradesMan;
import cc2.use_cases.tradesman.domain.TradesManId;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TradesManController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private final TradesManService tradesManService;

    public TradesManController(CommandBus commandBus, QueryBus queryBus, TradesManService tradesManService) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.tradesManService = tradesManService;
    }


    public Router getSubRouter(final Vertx vertx) {
        final Router subRouter = Router.router(vertx);

        // Body handler
        subRouter.route("/*").handler(BodyHandler.create());

        // Routes
        subRouter.post("/").handler(this::createTrademan);
        subRouter.get("/:id").handler(this::getTradesManById);
        subRouter.get("/").handler(this::getAllTradesMan);
        subRouter.delete("/:id").handler(this::deleteTradesManById);

        return subRouter;
    }

    private void createTrademan(RoutingContext routingContext) {
        System.out.println("Dans le create TradesMan...");

        final JsonObject body = routingContext.getBodyAsJson();
        final String firstname = body.getString("firstname");
        final String lastname = body.getString("lastname");
        final String emailParam = body.getString("email");
        final String creditCardNumber = body.getString("creditCardNumber");
        final String ownerName = body.getString("ownerName");
        final String job = body.getString("job");
        final String skill = body.getString("skill");
        final Double dailyTax = Double.valueOf(body.getString("dailyTax"));
        final String region = body.getString("region");
        final String city = body.getString("city");
        final String diploma = body.getString("diploma");


        final JsonObject jsonResponse = new JsonObject();
        final JsonObject errorJsonResponse = new JsonObject();


        if(firstname == null || lastname == null || emailParam == null ||
                creditCardNumber == null || ownerName == null || job == null || skill == null ||
                 region == null || city == null || diploma == null){

            errorJsonResponse.put("error", "A param is missing");
            routingContext.response()
                    .setStatusCode(403)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
        }

        if(dailyTax == 0.0){
            errorJsonResponse.put("error", "DailTax mustn't be zero");
            routingContext.response()
                    .setStatusCode(403)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
        }


        final Email email = Email.of(emailParam);
        final CreditCard creditCard = CreditCard.of(creditCardNumber,ownerName, LocalDateTime.now());
        final List skills = new ArrayList();
        skills.add(skill);
        final Location location = Location.of("Ile-de-France", "Ermont");
        final List diplomas = new ArrayList();
        skills.add(diploma);

        final TradesManDTO tradesManDTO = new TradesManDTO(firstname, lastname, email, creditCard, job, skills,
                                                            dailyTax, location, diplomas);



        final TradesManId tradesManId = tradesManService.create(tradesManDTO);


        final CreateTradesMan createTradesMan = new CreateTradesMan(firstname, lastname, email, creditCard, job, skills,
                                                                    dailyTax, location, diplomas);

        commandBus.send(createTradesMan);

        jsonResponse.put("TradesMan", tradesManId);
        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json")
                .end(Json.encode(jsonResponse));
    }

    private void getTradesManById(RoutingContext routingContext) {
        System.out.println("Dans le getTradesManById...");

        final String id = routingContext.request().getParam("id");
        if(id == null) routingContext.response().setStatusCode(403).end("Id is missing");


        final TradesManId tradesManId = TradesManId.of(id);

        final TradesMan tradesMan = tradesManService.getById(tradesManId);

        if (tradesMan == null) {
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "No Tradesman can be found for the specified id : " + id);

            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
        }

        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(tradesMan.toString()));

    }


    private void getAllTradesMan(RoutingContext routingContext) {
        System.out.println("Dans getAllTradesMan...");


        final List<TradesMan> tradesManList = tradesManService.getAll();
        queryBus.send(new RetrieveTradesMans());


        if(tradesManList.isEmpty()){
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "No TradesMan");

            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
        }


        TradesMansResponse tradesMansResponse = new TradesMansResponse(
                tradesManList
                        .stream()
                        .map(tradesMan ->
                                new TradesManResponse(String.valueOf(tradesMan.getTradesManId().getId()),
                                        tradesMan.getLastname(), tradesMan.getFirstname(),
                                        Email.of(tradesMan.getEmail().toString()),
                                        CreditCard.of(tradesMan.getCreditCard().getNumber(), tradesMan.getCreditCard().getOwnerName(), tradesMan.getCreditCard().getExpirationDate()),
                                        tradesMan.getJob(), tradesMan.getSkills(), tradesMan.getDailyTax(),
                                        tradesMan.getLocation(), tradesMan.getDiplomas()))
                        .collect(Collectors.toList()));

        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("tradesManList", tradesMansResponse.tradesManResponseList.toString());
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(jsonResponse));
    }

    private void deleteTradesManById(RoutingContext routingContext) {
        System.out.println("Dans le deleteTradesManById...");

        final String idPram = routingContext.request().getParam("id");
        if(idPram == null) {
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "Id param is missing");

            routingContext.response()
                    .setStatusCode(403)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
        }

        final TradesManId tradesManId = TradesManId.of(idPram);

        final TradesMan tradesMan = tradesManService.getById(tradesManId);

        if (tradesMan == null) {
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "No TradesMan can be found for the specified id : " + idPram);

            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
            return;
        }

        tradesManService.delete(tradesManId);

        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("Ok", "TradesMan has been successfully deleted");

        routingContext.response()
                .setStatusCode(204)
                .putHeader("content-type", "application/json")
                .end(Json.encode(jsonResponse));
    }

}
