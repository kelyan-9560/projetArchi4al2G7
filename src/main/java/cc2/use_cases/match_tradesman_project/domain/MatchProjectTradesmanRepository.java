package cc2.use_cases.match_tradesman_project.domain;

import cc2.use_cases.project.domain.Project;
import cc2.use_cases.project.domain.ProjectId;
import cc2.use_cases.task.domain.Task;

import java.util.List;
import java.util.UUID;

public interface MatchProjectTradesmanRepository {
    void create(MatchProjectTradesman matchProjectTradesman);

    MatchProjectTradesman getById(MatchProjectTradesmanId matchProjectTradesmanId);

    List<MatchProjectTradesman> getAll();

    void delete(MatchProjectTradesmanId matchProjectTradesmanId);

    List<MatchProjectTradesman> addMatchProjectTradesman(MatchProjectTradesmanId matchProjectTradesmanId, MatchProjectTradesman matchProjectTradesman);

    default MatchProjectTradesmanId nextId(){
        return MatchProjectTradesmanId.fromUUID(UUID.randomUUID());
    }

}
