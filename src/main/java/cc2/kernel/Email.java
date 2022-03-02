package cc2.kernel;

import java.util.Objects;

public final class Email {
    private final String email;

    public Email(String email) {
        this.email = email;
    }

    public static Email of(String email){
        return new Email(email);
    }


    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
