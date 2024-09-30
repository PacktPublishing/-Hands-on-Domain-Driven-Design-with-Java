package expert.os.books.ddd.chapter04;

import javax.money.MonetaryAmount;

public class HiringService {

    private final FinanceService financeService;
    private final EmailService emailService;

    public HiringService(FinanceService financeService, EmailService emailService) {
        this.financeService = financeService;
        this.emailService = emailService;
    }

    public void hirePlayer(Team team, Player player, MonetaryAmount salary) {
        if (!team.canAdd()) {
            throw new IllegalStateException("Team already has maximum players");
        }

        financeService.processSalary(player, salary);
        team.add(player);

        emailService.sendHiringNotification(player, team);

        System.out.println("Player " + player.getName() + " hired for team " + team.getName());
    }
}
