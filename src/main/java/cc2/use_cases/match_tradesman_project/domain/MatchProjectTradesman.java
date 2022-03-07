package cc2.use_cases.match_tradesman_project.domain;

import cc2.use_cases.project.domain.Project;
import cc2.use_cases.tradesman.domain.TradesMan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public final class MatchProjectTradesman {

    private final MatchProjectTradesmanId matchProjectTradesmanId;
    private final Project project;
    private final List<TradesMan> tradesManList;
    private TradesMan bestFitTradesMan;

    public MatchProjectTradesman(MatchProjectTradesmanId matchProjectTradesmanId, Project project, List<TradesMan> tradesManList) {
        this.matchProjectTradesmanId = Objects.requireNonNull(matchProjectTradesmanId);
        this.project = Objects.requireNonNull(project);
        this.tradesManList = tradesManList;
        this.bestFitTradesMan = null;
    }

    public static MatchProjectTradesman of(MatchProjectTradesmanId matchProjectTradesmanId, Project project, List<TradesMan> tradesManList) {
        return new MatchProjectTradesman(matchProjectTradesmanId, project, tradesManList);
    }

    public MatchProjectTradesmanId getMatchProjectTradesmanId() {
        return matchProjectTradesmanId;
    }

    public Project getProject() { return project; }

    public  List<TradesMan> getTradesManList() { return tradesManList; }

    public TradesMan getBestFitTradesMan(){
        return this.bestFitTradesMan;
    }

    public List<TradesMan> searchTradesManCandidates(){
        List <TradesMan> candidates = new ArrayList<TradesMan>();
        for (TradesMan tradesMan: this.tradesManList) {
            if(matchedAttributesCounter(tradesMan) > 0) candidates.add(tradesMan);
        }
        return candidates;
    }

    public int matchedAttributesCounter(TradesMan tradesMan){
        int counter = 0;
        //parcourir les besoins du projet(diplomes, taches etc...),
        //si le trademan a cet attribut, counter ++;
        return counter;
    }

    public void assignBestFitTradesMan(){
        List <TradesMan> candidates = searchTradesManCandidates();
        Iterator<TradesMan> tradesManListIterator = this.tradesManList.iterator();
        TradesMan bestFitTradesman = tradesManListIterator.hasNext() ? tradesManListIterator.next() : null;
        TradesMan nextTradesMan = null;
        while (tradesManListIterator.hasNext()){
            nextTradesMan = tradesManListIterator.next();
            if(matchedAttributesCounter(nextTradesMan) > matchedAttributesCounter(bestFitTradesMan)){
                bestFitTradesman = nextTradesMan;
            }
        }

        this.bestFitTradesMan = bestFitTradesman;
    }

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
