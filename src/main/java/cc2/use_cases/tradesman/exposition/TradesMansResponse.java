package cc2.use_cases.tradesman.exposition;


import java.util.List;

public class TradesMansResponse {

    public final List<TradesManResponse> tradesManResponseList;

    public TradesMansResponse(List<TradesManResponse> tradesManResponseList) {
        this.tradesManResponseList = tradesManResponseList;
    }
}
