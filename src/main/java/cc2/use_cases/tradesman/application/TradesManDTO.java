package cc2.use_cases.tradesman.application;

import cc2.use_cases.tradesman.domain.Location;
import cc2.use_cases.tradesman.domain.CreditCard;
import cc2.use_cases.tradesman.domain.Email;
import org.springframework.lang.NonNull;

public class TradesManDTO {

    @NonNull
    public String firstname;
    @NonNull
    public String lastname;
    @NonNull
    public Email email;
    @NonNull
    public CreditCard creditCard;
    @NonNull
    public String job;
    @NonNull
    public String skill;
    @NonNull
    public Double dailyTax;
    @NonNull
    public Location location;
    @NonNull
    public String diplomas;

    public TradesManDTO(@NonNull String firstname, @NonNull String lastname, @NonNull Email email, @NonNull CreditCard creditCard,
                        @NonNull String job, @NonNull String skill, @NonNull Double dailyTax, @NonNull Location location, @NonNull String diplomas) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.creditCard = creditCard;
        this.job = job;
        this.skill = skill;
        this.dailyTax = dailyTax;
        this.location = location;
        this.diplomas = diplomas;
    }

    @Override
    public String toString() {
        return "TradesManDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", creditCard=" + creditCard +
                ", job='" + job + '\'' +
                ", skill='" + skill + '\'' +
                ", dailyTax=" + dailyTax +
                ", location=" + location +
                ", diplomas='" + diplomas + '\'' +
                '}';
    }
}
