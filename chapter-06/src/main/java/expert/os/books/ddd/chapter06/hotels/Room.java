package expert.os.books.ddd.chapter06.hotels;

import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Room {

    static final Guest EMPTY_GUEST = new Guest("0", "EMPTY");

    @Identity
    private int number;

    private Guest guest;

    public int getNumber() {
        return number;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void cleanRoom() {
        this.guest = EMPTY_GUEST;
    }

    public Room(int number, Guest guest) {
        this.number = number;
        this.guest = guest;
    }


}
