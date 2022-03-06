package cc2.use_cases.contractor.application;

import cc2.kernel.Command;
import cc2.kernel.CreditCard;
import cc2.kernel.Email;
import cc2.kernel.Password;

public final class CreateContractor implements Command {

    public final String firstname;
    public final String lastname;
    public final Password password;
    public final CreditCard creditCard;
    public final Email email;

    public CreateContractor(String firstname, String lastname, Password password, CreditCard creditCard, Email email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.creditCard = creditCard;
        this.email = email;
    }
}
