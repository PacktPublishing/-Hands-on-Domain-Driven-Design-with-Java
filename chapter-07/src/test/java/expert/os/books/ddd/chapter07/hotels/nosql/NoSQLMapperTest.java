package expert.os.books.ddd.chapter07.hotels.nosql;

import expert.os.books.ddd.chapter07.hotels.Guest;
import expert.os.books.ddd.chapter07.hotels.Room;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class NoSQLMapperTest {

    private NoSQLMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(NoSQLMapper.class);
    }

    @Test
    void shouldMapGuestToEntity() {
        var guest = new Guest("12345", "John Doe");
        var guestNoSQL = mapper.toEntity(guest);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(guestNoSQL.getDocumentNumber()).isEqualTo(guest.documentNumber());
            softly.assertThat(guestNoSQL.getName()).isEqualTo(guest.name());
        });

    }

    @Test
    void shouldMapGuestToDomain() {
        var guestNoSQL = new GuestNoSQL("12345", "John Doe");
        var guest = mapper.toDomain(guestNoSQL);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(guest.documentNumber()).isEqualTo(guestNoSQL.getDocumentNumber());
            softly.assertThat(guest.name()).isEqualTo(guestNoSQL.getName());
        });
    }


    @Test
    void shouldMapRoomToEntity() {
        var guest = new Guest("12345", "John Doe");
        var room = new Room(101, guest);
        var roomNoSQL = mapper.toEntity(room);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(roomNoSQL.getNumber()).isEqualTo(room.getNumber());
            softly.assertThat(roomNoSQL.getGuest()).isNotNull();
        });
    }

    @Test
    void shouldMapRoomToDomain() {
        var guestNoSQL = new GuestNoSQL("12345", "John Doe");
        var roomNoSQL = new RoomNoSQL(101L, guestNoSQL);
        var guest = mapper.toDomain(guestNoSQL);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(guest.documentNumber()).isEqualTo(guestNoSQL.getDocumentNumber());
            softly.assertThat(guest.name()).isEqualTo(guestNoSQL.getName());
        });
    }

}
