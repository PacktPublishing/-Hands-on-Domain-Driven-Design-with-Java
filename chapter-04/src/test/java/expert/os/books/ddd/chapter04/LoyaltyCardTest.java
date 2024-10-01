package expert.os.books.ddd.chapter04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LoyaltyCardTest {

    @ParameterizedTest
    @CsvSource({
            "0, BRONZE",
            "999, BRONZE",
            "1000, SILVER",
            "1500, SILVER",
            "5000, GOLD",
            "7000, GOLD",
            "10000, PLATINUM",
            "15000, PLATINUM"
    })
    @DisplayName("should return the correct category based on points")
    void shouldReturnCorrectCategoryBasedOnPoints(int points, String expectedCategory) {
        Customer customer = new Customer("John Doe", "john.doe@example.com");
        LoyaltyCard card = new LoyaltyCard("1", customer);
        card.addPoints(points);

        assertThat(card.getCategory().toString()).isEqualTo(expectedCategory);
    }

    @ParameterizedTest
    @CsvSource({
            "100, 100, 200",
            "500, 1000, 1500",
            "0, 500, 500"
    })
    @DisplayName("should add points to loyalty card correctly")
    void shouldAddPointsCorrectly(int initialPoints, int additionalPoints, int expectedTotalPoints) {
        Customer customer = new Customer("John Doe", "john.doe@example.com");
        LoyaltyCard card = new LoyaltyCard("1", customer);
        card.addPoints(initialPoints);

        card.addPoints(additionalPoints);

        assertThat(card.getPoints()).isEqualTo(expectedTotalPoints);
    }

    @ParameterizedTest
    @CsvSource({
            "999, false",
            "1000, true",
            "1500, true",
            "5000, true"
    })
    @DisplayName("should correctly determine if premium status is reached")
    void shouldCorrectlyDeterminePremiumStatus(int points, boolean expectedIsPremium) {
        Customer customer = new Customer("Jane Doe", "jane.doe@example.com");
        LoyaltyCard card = new LoyaltyCard("1", customer);
        card.addPoints(points);

        assertThat(card.isPremium()).isEqualTo(expectedIsPremium);
    }

    @ParameterizedTest
    @CsvSource({
            "1000, SILVER",
            "5000, GOLD",
            "10000, PLATINUM"
    })
    @DisplayName("should update category correctly when points added")
    void shouldUpdateCategoryCorrectlyWhenPointsAdded(int points, String expectedCategory) {
        Customer customer = new Customer("John Doe", "john.doe@example.com");
        LoyaltyCard card = new LoyaltyCard("1", customer);

        card.addPoints(points);

        assertThat(card.getCategory().toString()).isEqualTo(expectedCategory);
    }

    @ParameterizedTest
    @CsvSource({
            "0, BRONZE",
            "1000, SILVER",
            "5000, GOLD",
            "10000, PLATINUM"
    })
    @DisplayName("should correctly return category after reaching points thresholds")
    void shouldCorrectlyReturnCategoryAfterReachingPointsThresholds(int points, String expectedCategory) {
        Customer customer = new Customer("John Doe", "john.doe@example.com");
        LoyaltyCard card = new LoyaltyCard("1", customer);
        card.addPoints(points);

        assertThat(card.getCategory().toString()).isEqualTo(expectedCategory);
    }
}
