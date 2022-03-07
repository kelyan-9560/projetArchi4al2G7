package cc2.use_cases.match_tradesman_project.domain;

import cc2.use_cases.project.domain.Project;
import cc2.use_cases.tradesman.domain.TradesMan;

import java.util.List;
import java.util.Objects;

public final class MatchProjectTradesman {

    private final MatchProjectTradesmanId matchProjectTradesmanId;
    private final Project project;
    private final List<TradesMan> tradesManList;

    public MatchProjectTradesman(MatchProjectTradesmanId matchProjectTradesmanId, Project project, List<TradesMan> tradesManList) {
        this.matchProjectTradesmanId = Objects.requireNonNull(matchProjectTradesmanId);
        this.project = Objects.requireNonNull(project);
        this.tradesManList = tradesManList;
    }

    public static MatchProjectTradesman of(MatchProjectTradesmanId matchProjectTradesmanId, Project project, List<TradesMan> tradesManList) {
        return new MatchProjectTradesman(matchProjectTradesmanId, project, tradesManList);
    }

    public MatchProjectTradesmanId getMatchProjectTradesmanId() {
        return matchProjectTradesmanId;
    }

    public Project getProject() { return project; }

    public  List<TradesMan> getTradesManList() { return tradesManList; }

    @Override
    public int hashCode() {
        return Objects.hash(matchProjectTradesmanId, project, tradesManList);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
