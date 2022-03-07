package cc2;

import cc2.use_cases.contractor.exposition.ContractorController;
import cc2.use_cases.tradesman.exposition.TradesManController;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class ApiVerticle extends AbstractVerticle {

    private final ContractorController contractorController;
    private final TradesManController tradesManController;

    public ApiVerticle(ContractorController contractorController, TradesManController tradesManController) {
        this.contractorController = contractorController;
        this.tradesManController = tradesManController;
    }

    @Override
    public void start() throws Exception {
        System.out.println("Dans le start...");

        Router router = Router.router(vertx);
        final Router contractorSubRouter = contractorController.getSubRouter(vertx);
        final Router tradesManSubRouter = tradesManController.getSubRouter(vertx);

        router.mountSubRouter("/api/v1/contractor", contractorSubRouter);
        router.mountSubRouter("/api/v1/tradesman", tradesManSubRouter);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(3000);
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Dans le stop...");
    }
}
