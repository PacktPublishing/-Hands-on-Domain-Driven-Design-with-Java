package expert.os.books.ddd.chapter06.hotels.controller;

import expert.os.books.ddd.chapter06.hotels.CheckInUseCase;
import expert.os.books.ddd.chapter06.hotels.CheckOutUseCase;
import expert.os.books.ddd.chapter06.hotels.Room;
import org.jmolecules.architecture.hexagonal.Port;

//Here we hava either a controller or a resource to expose the application services
@Port
public class HotelController {

    private final CheckInUseCase checkInUseCase;
    private final CheckOutUseCase checkOutUseCase;
    private final HotelMapper hotelMapper;

    public HotelController(CheckInUseCase checkInUseCase, CheckOutUseCase checkOutUseCase, HotelMapper hotelMapper) {
        this.checkInUseCase = checkInUseCase;
        this.checkOutUseCase = checkOutUseCase;
        this.hotelMapper = hotelMapper;
    }

    public RoomDTO checkInRoom(GuestDTO guestDTO) {
        var guest = hotelMapper.toEntity(guestDTO);
        Room room = checkInUseCase.checkIn(guest);
        return hotelMapper.toDTO(room);
    }

    public void checkOutRoom(RoomDTO roomDTO) {
        var room = hotelMapper.toEntity(roomDTO);
        checkOutUseCase.checkOut(room);
    }
}
