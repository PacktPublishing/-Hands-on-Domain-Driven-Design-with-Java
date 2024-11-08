package expert.os.books.ddd.chapter07.hotels;

import org.jmolecules.ddd.annotation.Repository;

import java.util.Optional;

@Repository
public interface Hotel {

    Room checkIn(Room room);

    void checkOut(Room room);

    Optional<Room> reservation(String number);

    Long countBy();

    Optional<Room> findEmptyRoom();
}
