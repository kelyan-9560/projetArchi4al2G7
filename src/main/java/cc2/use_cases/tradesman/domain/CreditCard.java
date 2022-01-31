package cc2.use_cases.tradesman.domain;

import java.time.LocalDateTime;
import java.util.Objects;


public final class CreditCard {

    private final String number;
    private final String ownerName;
    private final LocalDateTime expirationDate;


    public CreditCard(String number, String ownerName, LocalDateTime expirationDate) {
        this.number = Objects.requireNonNull(number);
        this.ownerName = Objects.requireNonNull(ownerName);
        this.expirationDate = Objects.requireNonNull(expirationDate);

    }
    
    public static CreditCard of(String number, String ownerName, LocalDateTime expirationDate){
        return new CreditCard(number, ownerName, expirationDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(number, that.number) && Objects.equals(ownerName, that.ownerName) && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, ownerName, expirationDate);
    }

    public String getNumber() {
        return number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
}
