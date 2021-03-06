package cc2.use_cases.tradesman.domain;

import cc2.kernel.CreditCard;
import cc2.kernel.Email;
import cc2.kernel.Location;

import java.util.List;
import java.util.Objects;

public final class TradesMan {

    private final TradesManId tradesManId;
    private final String firstname;
    private final String lastname;
    private final Email email;
    private final CreditCard creditCard;
    private final String job;
    private final List<String> skills;
    private final Double dailyTax;
    private final Location location;
    private final List<Diploma> diplomas;

    private TradesMan(TradesManId tradesManId, String firstname, String lastname, Email email,
                      CreditCard creditCard, String job, List<String> skills, Double dailyTax, Location location, List<Diploma> diplomas){

        this.tradesManId = Objects.requireNonNull(tradesManId);
        this.firstname = Objects.requireNonNull(firstname);
        this.lastname = Objects.requireNonNull(lastname);
        this.email = Objects.requireNonNull(email);
        this.creditCard = Objects.requireNonNull(creditCard);
        this.job = Objects.requireNonNull(job);
        this.skills = Objects.requireNonNull(skills);
        this.dailyTax = Objects.requireNonNull(dailyTax);
        this.location = Objects.requireNonNull(location);
        this.diplomas = Objects.requireNonNull(diplomas);
    }

    public static TradesMan of(TradesManId tradesManId, String firstname, String lastname, Email email,
                               CreditCard creditCard, String job, List<String> skills, Double dailyTax, Location location,
                               List<Diploma> diplomas){
        return new TradesMan(tradesManId, firstname, lastname, email, creditCard, job, skills, dailyTax, location, diplomas);
    }

    @Override
    public String toString() {
        return "TradesMan{" +
                "tradesManId=" + tradesManId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email=" + email +
                ", creditCard=" + creditCard +
                ", job='" + job + '\'' +
                ", skill='" + skills + '\'' +
                ", dailyTax=" + dailyTax +
                ", location=" + location +
                ", diplomas='" + diplomas + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradesMan tradesMan = (TradesMan) o;
        return Objects.equals(tradesManId, tradesMan.tradesManId) && Objects.equals(firstname, tradesMan.firstname) &&
                Objects.equals(lastname, tradesMan.lastname) && Objects.equals(email, tradesMan.email) &&
                Objects.equals(creditCard, tradesMan.creditCard) && Objects.equals(job, tradesMan.job) &&
                Objects.equals(skills, tradesMan.skills) && Objects.equals(dailyTax, tradesMan.dailyTax) &&
                Objects.equals(location, tradesMan.location) && Objects.equals(diplomas, tradesMan.diplomas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradesManId, firstname, lastname, email, creditCard, job, skills, dailyTax, location, diplomas);
    }

    public TradesManId getTradesManId() {
        return tradesManId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Email getEmail() {
        return email;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public String getJob() {
        return job;
    }

    public List<String> getSkills() {
        return skills;
    }

    public Double getDailyTax() {
        return dailyTax;
    }

    public Location getLocation() {
        return location;
    }

    public List<Diploma> getDiplomas() {
        return diplomas;
    }
}