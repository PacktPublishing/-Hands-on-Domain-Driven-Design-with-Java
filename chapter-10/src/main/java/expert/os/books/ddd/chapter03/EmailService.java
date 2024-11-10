package expert.os.books.ddd.chapter03;

public class EmailService {

    public void sendHiringNotification(Player player, Team team) {
        // Email sending logic here
        System.out.println("Email sent: Player " + player.getName() + " hired by team " + team.getName());
    }
}
