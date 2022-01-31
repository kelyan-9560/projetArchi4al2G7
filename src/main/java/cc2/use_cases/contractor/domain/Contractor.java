package cc2.use_cases.contractor.domain;

import java.util.Objects;

public final class Contractor {

    private final ContractorId contractorId;
    private final String firstname;
    private final String lastname;

    public Contractor(ContractorId contractorId, String firstname, String lastname) {
        this.contractorId = Objects.requireNonNull(contractorId);
        this.firstname = Objects.requireNonNull(firstname);
        this.lastname = Objects.requireNonNull(lastname);
    }

    public static Contractor of(ContractorId contractorId, String firstname, String lastname){
        return new Contractor(contractorId, firstname, lastname);
    }

    @Override
    public String toString() {
        return "Contractor{" +
                "contractorId=" + contractorId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contractor that = (Contractor) o;
        return Objects.equals(contractorId, that.contractorId) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractorId, firstname, lastname);
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
}
