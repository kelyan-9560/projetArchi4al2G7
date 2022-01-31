package cc2.use_cases.project.domain;


import java.util.Objects;
import java.util.UUID;

public final class ProjectId {

    private final String id;

    private ProjectId(String id) {
        this.id = id;
    }

    public static ProjectId of(String id) {
        return new ProjectId(id);
    }

    public static ProjectId fromUUID(UUID uuid) {
        return new ProjectId(uuid.toString());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectId projectId = (ProjectId) o;
        return Objects.equals(id, projectId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
