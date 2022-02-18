package cc2.use_cases.contractor.application;

import cc2.kernel.Password;
import cc2.kernel.CreditCard;
import cc2.kernel.Email;
import org.springframework.lang.NonNull;

public class ContractorDTO {

    @NonNull
    public String firstname;
    @NonNull
    public String lastname;
    @NonNull
    public Password password;
    @NonNull
    public CreditCard creditCard;
    @NonNull
    public Email email;

    public ContractorDTO(@NonNull String firstname, @NonNull String lastname, @NonNull Password password, @NonNull CreditCard creditCard, @NonNull Email email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.creditCard = creditCard;
        this.email = email;
    }
}
