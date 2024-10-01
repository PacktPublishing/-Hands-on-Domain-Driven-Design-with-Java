package expert.os.books.ddd.chapter04;

public record LoyaltyPoints(int points) {

    public LoyaltyPoints addPoints(int additionalPoints) {
        return new LoyaltyPoints(this.points + additionalPoints);
    }

    public boolean canUpgradeToPremium() {
        return this.points >= 1000;
    }
}
