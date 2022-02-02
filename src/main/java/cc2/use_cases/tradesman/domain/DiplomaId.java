package cc2.use_cases.tradesman.domain;

import java.util.UUID;

public final class DiplomaId {

    private final String id;

    public DiplomaId(String id) {
        this.id = id;
    }

    public static DiplomaId of(String id) {
        return new DiplomaId(id);
    }

    public static DiplomaId fromUUID(UUID uuid) {
        return new DiplomaId(uuid.toString());
    }

    public String getId() {
        return id;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "DiplomaId{" +
                "id='" + id + '\'' +
                '}';
    }
}
