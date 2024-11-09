package expert.os.books.ddd.chapter07.hotels.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class RoomJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @OneToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private GuestJPA guest;


    public void cleanRoom() {
        this.guest = null;
    }
}
