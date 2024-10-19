package expert.os.books.ddd.chapter04.hotels;

import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Room {

    @Identity
    private int number;

    private Guest guest;

}
