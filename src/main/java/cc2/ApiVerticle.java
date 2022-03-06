package cc2;

import cc2.use_cases.contractor.exposition.ContractorController;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class ApiVerticle extends AbstractVerticle {

    private final ContractorController contractorController;

    public ApiVerticle(ContractorController contractorController) {
        this.contractorController = contractorController;
    }

    @Override
    public void start() throws Exception {
        System.out.println("Dans le start...");

        Router router = Router.router(vertx);
        final Router contractorSubRouter = contractorController.getSubRouter(vertx);

        router.mountSubRouter("/api/v1/contractor", contractorSubRouter);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(3000);
    }

    @Override
    public void stop() throws Exception {
        //LOGGER.info("Dans le stop...");
        System.out.println("Dans le stop...");
    }
}
