package expert.os.books.ddd.chapter05.hotels.controller;

import expert.os.books.ddd.chapter05.hotels.Guest;
import expert.os.books.ddd.chapter05.hotels.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelMapper  {

    HotelMapper INSTANCE = Mappers.getMapper( HotelMapper.class );

    RoomDTO toDTO(Room room);

    Room toEntity(RoomDTO roomDTO);

    GuestDTO toDTO(Guest guest);

    Guest toEntity(GuestDTO guestDTO);
}
