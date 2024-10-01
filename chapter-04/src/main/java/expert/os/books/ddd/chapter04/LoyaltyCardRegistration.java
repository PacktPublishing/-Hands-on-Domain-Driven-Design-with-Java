package expert.os.books.ddd.chapter04;

import java.time.LocalDate;

public class LoyaltyCardRegistration {
    private final String id;
    private final Customer customer;
    private final LocalDate registrationDate;
    private CardStatus status;

    public LoyaltyCardRegistration(String id, Customer customer, LocalDate registrationDate) {
        this.id = id;
        this.customer = customer;
        this.registrationDate = registrationDate;
        this.status = CardStatus.ACTIVE;
    }
}
