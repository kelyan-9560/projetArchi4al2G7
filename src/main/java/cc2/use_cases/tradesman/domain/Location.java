package cc2.use_cases.tradesman.domain;

import java.util.Objects;

public final class Location {

    private final String region;
    private final String city;

    public Location(String region, String city) {
        this.region = Objects.requireNonNull(region);
        this.city = Objects.requireNonNull(city);
    }

    public static Location of(String region, String city){
        return new Location(region, city);
    }

    @Override
    public String toString() {
        return "Location{" +
                "region='" + region + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(region, location.region) && Objects.equals(city, location.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, city);
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }
}
