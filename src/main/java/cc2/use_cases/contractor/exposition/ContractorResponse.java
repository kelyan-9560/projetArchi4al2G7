package cc2.use_cases.contractor.exposition;

import cc2.kernel.CreditCard;
import cc2.kernel.Email;
import cc2.kernel.Password;

public class ContractorResponse {

    public String id;
    public String firstname;
    public String lastname;
    public Password password;
    public CreditCard creditCard;
    public Email email;


    public ContractorResponse(String id, String firstname, String lastname, Password password,
                              CreditCard creditCard, Email email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.creditCard = creditCard;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContractorResponse{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password=" + password +
                ", creditCard=" + creditCard +
                ", email=" + email +
                '}';
    }
}
