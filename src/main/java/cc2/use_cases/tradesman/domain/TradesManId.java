package cc2.use_cases.tradesman.domain;

import java.util.Objects;
import java.util.UUID;

public final class TradesManId {

    private final String id;

    public TradesManId(String id) {
        this.id = id;
    }

    public static TradesManId of(String id) {
        return new TradesManId(id);
    }

    public static TradesManId fromUUID(UUID uuid) {
        return new TradesManId(uuid.toString());
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradesManId that = (TradesManId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TradesManId{" +
                "id='" + id + '\'' +
                '}';
    }
}
