package expert.os.books.ddd.chapter07.hotels.jpa;

import expert.os.books.ddd.chapter07.hotels.Guest;
import expert.os.books.ddd.chapter07.hotels.Room;
import org.mapstruct.Mapper;

@Mapper
public interface JPAMapper {

    GuestJPA toEntity(Guest guest);

    Guest toDomain(GuestJPA guestJPA);

    RoomJPA toEntity(Room room);

    Room toDomain(RoomJPA roomJPA);

}
