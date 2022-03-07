package cc2.use_cases.match_tradesman_project.domain;

import java.util.Objects;
import java.util.UUID;

public final class MatchProjectTradesmanId {
    private final String id;

    private MatchProjectTradesmanId(String id) {
        this.id = id;
    }

    public static MatchProjectTradesmanId of(String id) {
        return new MatchProjectTradesmanId(id);
    }

    public static MatchProjectTradesmanId fromUUID(UUID uuid) {
        return new MatchProjectTradesmanId(uuid.toString());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchProjectTradesmanId matchProjectTradesmanId = (MatchProjectTradesmanId) o;
        return Objects.equals(id, matchProjectTradesmanId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
