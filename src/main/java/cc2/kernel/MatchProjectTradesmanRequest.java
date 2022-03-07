package cc2.kernel.exposition;

import cc2.use_cases.project.domain.Project;
import cc2.use_cases.tradesman.domain.TradesMan;
import org.springframework.lang.NonNull;

import java.util.List;

public final class MatchProjectTradesmanRequest {

    @NonNull
    public Project project;

    @NonNull
    public List<TradesMan> tradesManList;

    public MatchProjectTradesmanRequest(@NonNull Project project, @NonNull List<TradesMan> tradesManList) {
        this.project = project;
        this.tradesManList = tradesManList;
    }
}
