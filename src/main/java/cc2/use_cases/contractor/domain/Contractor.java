package cc2.use_cases.contractor.domain;

import cc2.kernel.Password;
import cc2.kernel.CreditCard;
import cc2.kernel.Email;

import java.util.Objects;

public final class Contractor {

    private final ContractorId contractorId;
    private final String firstname;
    private final String lastname;
    private final Password password;
    private final CreditCard creditCard;
    private final Email email;

    public Contractor(ContractorId contractorId, String firstname, String lastname, Password password, CreditCard creditCard,Email email) {
        this.contractorId = Objects.requireNonNull(contractorId);
        this.firstname = Objects.requireNonNull(firstname);
        this.lastname = Objects.requireNonNull(lastname);
        this.password = Objects.requireNonNull(password);
        this.creditCard = Objects.requireNonNull(creditCard);
        this.email = Objects.requireNonNull(email);
    }

    public static Contractor of(ContractorId contractorId, String firstname, String lastname, Password password, CreditCard creditCard, Email email){
        return new Contractor(contractorId, firstname, lastname, password, creditCard, email);
    }

    @Override
    public String toString() {
        return "Contractor{" +
                "contractorId=" + contractorId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password=" + password +
                ", creditCard=" + creditCard +
                ", email=" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contractor that = (Contractor) o;
        return Objects.equals(contractorId, that.contractorId) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(password, that.password) && Objects.equals(creditCard, that.creditCard) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractorId, firstname, lastname, password, creditCard, email);
    }

    public ContractorId getContractorId() {
        return contractorId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Password getPassword() {
        return password;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Email getEmail() {
        return email;
    }
}
