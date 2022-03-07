package cc2.kernel;

import cc2.use_cases.project.domain.Project;
import cc2.use_cases.tradesman.domain.TradesMan;
import org.springframework.lang.NonNull;

import java.util.List;

public final class MatchProjectTradesmanRequest {

    @NonNull
    public Project project;

    @NonNull
    public List<TradesMan> tradesManList;

    @NonNull
    public TradesMan bestFitTradesMan;

    public MatchProjectTradesmanRequest(@NonNull Project project, @NonNull List<TradesMan> tradesManList, TradesMan bestFitTradesMan) {
        this.project = project;
        this.tradesManList = tradesManList;
        this.bestFitTradesMan = bestFitTradesMan;
    }
}
