package expert.os.books.ddd.chapter04.hotels.application;

import expert.os.books.ddd.chapter04.hotels.Guest;
import expert.os.books.ddd.chapter04.hotels.Room;
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
