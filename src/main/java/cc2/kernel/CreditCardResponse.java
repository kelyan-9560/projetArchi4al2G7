package cc2.kernel;

import java.time.LocalDateTime;

public class CreditCardResponse {

    public String number;
    public String ownerName;
    public LocalDateTime expirationDate;

    public CreditCardResponse(String number, String ownerName, LocalDateTime expirationDate) {
        this.number = number;
        this.ownerName = ownerName;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "CreditCardResponse{" +
                "number='" + number + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}

