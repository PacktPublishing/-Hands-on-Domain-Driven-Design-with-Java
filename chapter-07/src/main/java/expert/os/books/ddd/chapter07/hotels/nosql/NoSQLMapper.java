package expert.os.books.ddd.chapter07.hotels.nosql;

import expert.os.books.ddd.chapter07.hotels.Guest;
import expert.os.books.ddd.chapter07.hotels.Room;
import org.mapstruct.Mapper;

@Mapper
interface NoSQLMapper {

    GuestNoSQL toEntity(Guest guest);

    Guest toDomain(GuestNoSQL guestJPA);

    RoomNoSQL toEntity(Room room);

    Room toDomain(RoomNoSQL roomJPA);

}
