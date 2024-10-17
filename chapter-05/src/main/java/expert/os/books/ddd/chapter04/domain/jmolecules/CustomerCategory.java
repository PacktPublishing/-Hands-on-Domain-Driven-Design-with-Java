package expert.os.books.ddd.chapter04.domain.jmolecules;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public enum CustomerCategory {

    BRONZE(0, 0),
    SILVER(1000, 5),
    GOLD(5000, 10),
    PLATINUM(10_000, 15);

    private final int pointsThreshold;
    private final int discountPercentage;

    CustomerCategory(int pointsThreshold, int discountPercentage) {
        this.pointsThreshold = pointsThreshold;
        this.discountPercentage = discountPercentage;
    }

    public int pointsThreshold() {
        return pointsThreshold;
    }

    public int discountPercentage() {
        return discountPercentage;
    }

    public static CustomerCategory getCategoryByPoints(int points) {
        if (points >= PLATINUM.pointsThreshold) {
            return PLATINUM;
        } else if (points >= GOLD.pointsThreshold) {
            return GOLD;
        } else if (points >= SILVER.pointsThreshold) {
            return SILVER;
        } else {
            return BRONZE;
        }
    }

}
