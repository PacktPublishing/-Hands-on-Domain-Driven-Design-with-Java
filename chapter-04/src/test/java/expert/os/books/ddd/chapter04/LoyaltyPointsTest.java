package expert.os.books.ddd.chapter04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoyaltyPointsTest {

    @ParameterizedTest
    @ValueSource(ints = {100, 200, 300})
    @DisplayName("should add points correctly")
    void shouldAddPointsCorrectly(int additionalPoints) {
        LoyaltyPoints points = new LoyaltyPoints(100);
        LoyaltyPoints updatedPoints = points.addPoints(additionalPoints);

        assertThat(updatedPoints.points()).isEqualTo(100 + additionalPoints);
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 1500, 2000})
    @DisplayName("should return true if points are enough for premium")
    void shouldReturnTrueForPremiumEligibility(int points) {
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(points);

        assertThat(loyaltyPoints.canUpgradeToPremium()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 500, 0})
    @DisplayName("should return false if points are not enough for premium")
    void shouldReturnFalseForPremiumEligibility(int points) {
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(points);

        assertThat(loyaltyPoints.canUpgradeToPremium()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 200})
    @DisplayName("should create new LoyaltyPoints object after adding points")
    void shouldCreateNewObjectAfterAddingPoints(int additionalPoints) {
        LoyaltyPoints points = new LoyaltyPoints(100);
        LoyaltyPoints updatedPoints = points.addPoints(additionalPoints);

        assertThat(updatedPoints).isNotSameAs(points);
        assertThat(updatedPoints.points()).isEqualTo(100 + additionalPoints);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 100, 999})
    @DisplayName("should correctly initialize with initial points")
    void shouldCorrectlyInitializeWithPoints(int initialPoints) {
        LoyaltyPoints points = new LoyaltyPoints(initialPoints);

        assertThat(points.points()).isEqualTo(initialPoints);
    }
}
