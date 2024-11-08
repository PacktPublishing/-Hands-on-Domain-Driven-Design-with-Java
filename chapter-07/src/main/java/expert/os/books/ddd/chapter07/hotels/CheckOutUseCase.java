package expert.os.books.ddd.chapter07.hotels;

import org.jmolecules.architecture.hexagonal.Application;

@Application
public class CheckOutUseCase {

    private final Hotel hotel;

    public CheckOutUseCase(Hotel hotel) {
        this.hotel = hotel;
    }

    public void checkOut(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }

        room.cleanRoom();
        hotel.checkOut(room);
    }
}
