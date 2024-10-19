package expert.os.books.ddd.chapter04.hotels;

import org.jmolecules.ddd.annotation.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class HotelService {

    private final Hotel hotel;

    private final PaymentService paymentService;

    public HotelService(Hotel hotel, PaymentService paymentService) {
        this.hotel = hotel;
        this.paymentService = paymentService;
    }

    public Room checkIn(Guest guest) {
        if (hotel.countBy() >= 100) {
            throw new IllegalStateException("Hotel is full");
        }

        var emptyRoom = hotel.findEmptyRoom().orElseThrow(() -> new IllegalStateException("No empty room"));
        paymentService.pay(guest);
        emptyRoom.setGuest(guest);
        return hotel.checkIn(emptyRoom);
    }

    public void checkOut(Room room) {
        Objects.requireNonNull(room, "Room is required");
        room.cleanRoom();
        hotel.checkOut(room);
    }

    public Optional<Room> reservation(String number) {
        return hotel.reservation(number);
    }
}
