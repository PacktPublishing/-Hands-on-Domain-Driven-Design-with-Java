package expert.os.books.ddd.chapter04.hotels.application;

import expert.os.books.ddd.chapter04.hotels.HotelService;
import expert.os.books.ddd.chapter04.hotels.Room;

//Here we hava either a controller or a resource to expose the application services
public class HotelController {

    private final HotelService hotelService;

    private final HotelMapper hotelMapper;

    public HotelController(HotelService hotelService, HotelMapper hotelMapper) {
        this.hotelService = hotelService;
        this.hotelMapper = hotelMapper;
    }

    public RoomDTO checkInRoom(GuestDTO guestDTO) {
        var guest = hotelMapper.toEntity(guestDTO);
        Room room = hotelService.checkIn(guest);
        return hotelMapper.toDTO(room);
    }

    public void checkOutRoom(RoomDTO roomDTO) {
        var room = hotelMapper.toEntity(roomDTO);
        hotelService.checkOut(room);
    }
}
