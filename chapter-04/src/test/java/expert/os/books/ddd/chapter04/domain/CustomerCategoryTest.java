package expert.os.books.ddd.chapter04.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomerCategoryTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 500, 999})
    @DisplayName("should return Bronze category for points less than 1000")
    void shouldReturnBronzeCategoryForPointsBelow1000(int points) {
        CustomerCategory category = CustomerCategory.getCategoryByPoints(points);

        assertThat(category).isEqualTo(CustomerCategory.BRONZE);
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 4999})
    @DisplayName("should return Silver category for points between 1000 and 4999")
    void shouldReturnSilverCategoryForPointsBetween1000And4999(int points) {
        CustomerCategory category = CustomerCategory.getCategoryByPoints(points);

        assertThat(category).isEqualTo(CustomerCategory.SILVER);
    }

    @ParameterizedTest
    @ValueSource(ints = {5000, 6000, 9999})
    @DisplayName("should return Gold category for points between 5000 and 9999")
    void shouldReturnGoldCategoryForPointsBetween5000And9999(int points) {
        CustomerCategory category = CustomerCategory.getCategoryByPoints(points);

        assertThat(category).isEqualTo(CustomerCategory.GOLD);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 15000})
    @DisplayName("should return Platinum category for points 10000 or more")
    void shouldReturnPlatinumCategoryForPoints10000OrMore(int points) {
        CustomerCategory category = CustomerCategory.getCategoryByPoints(points);

        assertThat(category).isEqualTo(CustomerCategory.PLATINUM);
    }

    @ParameterizedTest
    @ValueSource(ints = {5})
    @DisplayName("should return correct discount percentage for Silver category")
    void shouldReturnCorrectDiscountForSilverCategory(int expectedDiscount) {
        CustomerCategory category = CustomerCategory.SILVER;

        assertThat(category.discountPercentage()).isEqualTo(expectedDiscount);
    }

    @ParameterizedTest
    @ValueSource(ints = {5000})
    @DisplayName("should return correct points threshold for Gold category")
    void shouldReturnCorrectPointsThresholdForGoldCategory(int expectedThreshold) {
        CustomerCategory category = CustomerCategory.GOLD;

        assertThat(category.pointsThreshold()).isEqualTo(expectedThreshold);
    }
}
