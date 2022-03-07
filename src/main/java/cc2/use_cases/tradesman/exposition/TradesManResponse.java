package cc2.use_cases.tradesman.exposition;

import cc2.kernel.CreditCard;
import cc2.kernel.Email;
import cc2.kernel.Location;
import cc2.use_cases.tradesman.domain.Diploma;

import java.util.List;

public class TradesManResponse {
    public String tradesManId;
    public String firstname;
    public String lastname;
    public Email email;
    public CreditCard creditCard;
    public String job;
    public List<String> skills;
    public Double dailyTax;
    public Location location;
    public List<Diploma> diplomas;

    public TradesManResponse(String tradesManId, String firstname, String lastname, Email email, CreditCard creditCard,
                             String job, List<String> skills, Double dailyTax, Location location, List<Diploma> diplomas) {
        this.tradesManId = tradesManId;
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
