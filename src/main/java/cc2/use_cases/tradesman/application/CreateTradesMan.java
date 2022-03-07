package cc2.use_cases.tradesman.application;

import cc2.kernel.Command;
import cc2.kernel.CreditCard;
import cc2.kernel.Email;
import cc2.kernel.Location;
import cc2.use_cases.tradesman.domain.Diploma;

import java.util.List;

public final class CreateTradesMan implements Command {
    
    public final String firstname;
    public final String lastname;
    public final Email email;
    public final CreditCard creditCard;
    public final String job;
    public final List<String> skills;
    public final Double dailyTax;
    public final Location location;
    public final List<Diploma> diplomas;

    public CreateTradesMan(String firstname, String lastname, Email email, CreditCard creditCard, String job,
                           List<String> skills, Double dailyTax, Location location, List<Diploma> diplomas) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.creditCard = creditCard;
        this.job = job;
        this.skills = skills;
        this.dailyTax = dailyTax;
        this.location = location;
        this.diplomas = diplomas;
    }
}
