package expert.os.books.ddd.chapter04.effective;

import expert.os.books.ddd.chapter04.Customer;
import expert.os.books.ddd.chapter04.CustomerCategory;
import expert.os.books.ddd.chapter04.LoyaltyCard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class LoyaltyCardTest {

    @Test
    @DisplayName("Should correctly create LoyaltyCard with default category")
    void shouldCreateLoyaltyCardWithDefaultCategory() {
        Customer customer = new Customer("John Doe", "john.doe@example.com");

        LoyaltyCard card = new LoyaltyCard("1", customer);

        Assertions.assertThat(card.getCategory()).isEqualTo(CustomerCategory.BRONZE);
    }

    @Test
    @DisplayName("Should add points to LoyaltyCard and update category")
    void shouldAddPointsAndUpdateCategory() {
        Customer customer = new Customer("Jane Doe", "jane.doe@example.com");
        LoyaltyCard card = new LoyaltyCard("2", customer);

        card.addPoints(5000);

        Assertions.assertThat(card.getCategory()).isEqualTo(CustomerCategory.GOLD);
    }

    @ParameterizedTest
    @CsvSource({
            "0, BRONZE",
            "1000, SILVER",
            "5000, GOLD",
            "10000, PLATINUM"
    })
    @DisplayName("Should assign correct category based on points")
    void shouldAssignCorrectCategoryBasedOnPoints(int points, String expectedCategory) {
        Customer customer = new Customer("Jane Doe", "jane.doe@example.com");
        LoyaltyCard card = new LoyaltyCard("3", customer);
        card.addPoints(points);
        Assertions.assertThat(card.getCategory().toString()).isEqualTo(expectedCategory);
    }

    @Test
    @DisplayName("Should add points and validate multiple properties")
    void shouldAddPointsAndValidateProperties() {
        Customer customer = new Customer("Jane Doe", "jane.doe@example.com");
        LoyaltyCard card = new LoyaltyCard("4", customer);
        card.addPoints(5000);

        assertSoftly(softly -> {
            softly.assertThat(card.getPoints()).isEqualTo(5000);
            softly.assertThat(card.getCategory()).isEqualTo(CustomerCategory.GOLD);
            softly.assertThat(card.getCustomer().name()).isEqualTo("Jane Doe");
        });
    }


}
