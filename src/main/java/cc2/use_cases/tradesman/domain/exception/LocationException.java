package cc2.use_cases.tradesman.domain.exception;

public final class LocationException extends RuntimeException {

    public LocationException(int errorCode, LocationExceptionTags locationExceptionTags, String detail) {
    }

    public static LocationException withRegion(String region){
        return new LocationException(1, LocationExceptionTags.BAD_REGION, "Bad Region");
    }

    public static LocationException withCity(String city){
        return new LocationException(1, LocationExceptionTags.BAD_CITY,  "Bad city");
    }
}
