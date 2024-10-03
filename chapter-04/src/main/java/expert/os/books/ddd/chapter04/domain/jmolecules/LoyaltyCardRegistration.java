package expert.os.books.ddd.chapter04.domain.jmolecules;

import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

import java.time.LocalDate;

@Entity
public class LoyaltyCardRegistration {
    @Identity
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

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void suspend() {
        this.status = CardStatus.SUSPENDED;
    }

    public void activate() {
        this.status = CardStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = CardStatus.INACTIVE;
    }
}
