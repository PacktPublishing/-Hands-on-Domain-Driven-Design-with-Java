package expert.os.books.ddd.chapter07.hotels;


import org.jmolecules.architecture.hexagonal.Application;

@Application
public class CheckInUseCase {

    private final Hotel hotel;

    private final PaymentService paymentService;  // Domain service

    public CheckInUseCase(Hotel hotel, PaymentService paymentService) {
        this.hotel = hotel;
        this.paymentService = paymentService;
    }

    public Room checkIn(Guest guest) {
        if (hotel.countBy() >= 100) {
            throw new IllegalStateException("Hotel is full");
        }

        var emptyRoom = hotel.findEmptyRoom().orElseThrow(() -> new IllegalStateException("No empty room"));
        paymentService.pay(guest);  // Delegating domain logic to PaymentService
        emptyRoom.setGuest(guest);
        return hotel.checkIn(emptyRoom);
    }
}
