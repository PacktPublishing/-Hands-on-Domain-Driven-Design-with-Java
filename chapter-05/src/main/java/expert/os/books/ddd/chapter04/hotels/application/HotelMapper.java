package expert.os.books.ddd.chapter04.hotels.application;

import expert.os.books.ddd.chapter04.hotels.Guest;
import expert.os.books.ddd.chapter04.hotels.Room;

public interface HotelMapper  {

    RoomDTO toDTO(Room room);

    Room toEntity(RoomDTO roomDTO);

    GuestDTO toDTO(Guest guest);

    Guest toEntity(GuestDTO guestDTO);
}
