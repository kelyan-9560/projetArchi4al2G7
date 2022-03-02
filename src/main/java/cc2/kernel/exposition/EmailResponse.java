package cc2.kernel.exposition;

public class EmailResponse {

    private final String email;

    public EmailResponse(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailResponse{" +
                "email='" + email + '\'' +
                '}';
    }
}
