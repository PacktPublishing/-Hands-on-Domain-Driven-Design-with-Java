package expert.os.books.ddd.chapter04;

public class LoyaltyPoints {

    private int points;

    public LoyaltyPoints(int initialPoints) {
        this.points = initialPoints;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int additionalPoints) {
        this.points += additionalPoints;
    }

    public boolean canUpgradeToPremium() {
        return this.points >= 1000;
    }
}
