package expert.os.books.ddd.chapter03;

import javax.money.MonetaryAmount;

public class FinanceService {

    public void processSalary(Player player, MonetaryAmount salary) {
        // Process salary payment logic here
        System.out.println("Processing salary of " + salary + " for player " + player.getName());
    }
}
