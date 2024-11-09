package expert.os.books.ddd.chapter07.hotels.jpa;

import expert.os.books.ddd.chapter07.hotels.Guest;
import expert.os.books.ddd.chapter07.hotels.Room;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class JPAMapperTest {

    private JPAMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(JPAMapper.class);
    }

    @Test
    void shouldMapGuestToEntity() {
        var guest = new Guest("12345", "John Doe");
        var guestJPA = mapper.toEntity(guest);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(guestJPA.getDocumentNumber()).isEqualTo(guest.documentNumber());
            softly.assertThat(guestJPA.getName()).isEqualTo(guest.name());
        });

    }

    @Test
    void shouldMapGuestToDomain() {
        var guestJPA = new GuestJPA("12345", "John Doe");
        var guest = mapper.toDomain(guestJPA);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(guest.documentNumber()).isEqualTo(guestJPA.getDocumentNumber());
            softly.assertThat(guest.name()).isEqualTo(guestJPA.getName());
        });
    }


    @Test
    void shouldMapRoomToEntity() {
        var guest = new Guest("12345", "John Doe");
        var room = new Room(101, guest);
        var roomJPA = mapper.toEntity(room);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(roomJPA.getNumber()).isEqualTo(room.getNumber());
            softly.assertThat(roomJPA.getGuest()).isNotNull();
        });
    }

    @Test
    void shouldMapRoomToDomain() {
        var guestJPA = new GuestJPA("12345", "John Doe");
        var roomJPA = new RoomJPA(101L, guestJPA);
        var guest = mapper.toDomain(guestJPA);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(guest.documentNumber()).isEqualTo(guestJPA.getDocumentNumber());
            softly.assertThat(guest.name()).isEqualTo(guestJPA.getName());
        });
    }


}
