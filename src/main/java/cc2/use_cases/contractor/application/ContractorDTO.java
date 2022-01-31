package cc2.use_cases.contractor.application;

import org.springframework.lang.NonNull;

public class ContractorDTO {

    @NonNull
    public String firstname;
    @NonNull
    public String lastname;

    public ContractorDTO(@NonNull String firstname, @NonNull String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
