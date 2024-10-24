package expert.os.books.ddd.chapter04.hotels.infra;

import expert.os.books.ddd.chapter04.hotels.Hotel;
import expert.os.books.ddd.chapter04.hotels.Room;
import org.jmolecules.architecture.layered.InfrastructureLayer;

import java.util.Optional;

@InfrastructureLayer
public class MemoryHotel implements Hotel {


    @Override
    public Room checkIn(Room room) {
        return null;
    }

    @Override
    public void checkOut(Room room) {

    }

    @Override
    public Optional<Room> reservation(String number) {
        return Optional.empty();
    }

    @Override
    public Long countBy() {
        return 0L;
    }

    @Override
    public Optional<Room> findEmptyRoom() {
        return Optional.empty();
    }
}
