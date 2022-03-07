package cc2.use_cases.tradesman.application;

import cc2.use_cases.tradesman.domain.Diploma;
import cc2.kernel.Location;
import cc2.kernel.CreditCard;
import cc2.kernel.Email;
import org.springframework.lang.NonNull;

import java.util.List;

public final class TradesManDTO {

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
    public List<String> skills;
    @NonNull
    public Double dailyTax;
    @NonNull
    public Location location;
    @NonNull
    public List<Diploma> diplomas;

    public TradesManDTO(@NonNull String firstname, @NonNull String lastname, @NonNull Email email, @NonNull CreditCard creditCard,
                        @NonNull String job, @NonNull List<String> skills, @NonNull Double dailyTax, @NonNull Location location, @NonNull List<Diploma> diplomas) {
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

    @Override
    public String toString() {
        return "TradesManDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", creditCard=" + creditCard +
                ", job='" + job + '\'' +
                ", skill='" + skills + '\'' +
                ", dailyTax=" + dailyTax +
                ", location=" + location +
                ", diplomas='" + diplomas + '\'' +
                '}';
    }
}
