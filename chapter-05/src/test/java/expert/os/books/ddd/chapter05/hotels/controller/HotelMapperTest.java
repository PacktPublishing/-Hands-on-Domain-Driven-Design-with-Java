package expert.os.books.ddd.chapter05.hotels.controller;

import expert.os.books.ddd.chapter05.hotels.Guest;
import expert.os.books.ddd.chapter05.hotels.Room;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;


class HotelMapperTest {

    private HotelMapper hotelMapper = HotelMapper.INSTANCE;


    @Test
    void shouldMapRoomToRoomDTO() {

        var room = new Room(1, new Guest("1231", "John Doe"));

        var roomDTO = hotelMapper.toDTO(room);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(roomDTO).isNotNull();
            softly.assertThat(roomDTO.number()).isEqualTo(room.getNumber());
            var guest = roomDTO.guest();
            softly.assertThat(guest).isNotNull();
            softly.assertThat(guest.documentNumber()).isEqualTo(room.getGuest().documentNumber());
            softly.assertThat(guest.name()).isEqualTo(room.getGuest().name());
        });
    }


    @Test
    void shouldMapRoomDTOToRoom() {

        var roomDTO = new RoomDTO(1, new GuestDTO("1231", "John Doe"));

        var room = hotelMapper.toEntity(roomDTO);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(room).isNotNull();
            softly.assertThat(room.getNumber()).isEqualTo(roomDTO.number());
            var guest = room.getGuest();
            softly.assertThat(guest).isNotNull();
            softly.assertThat(guest.documentNumber()).isEqualTo(roomDTO.guest().documentNumber());
            softly.assertThat(guest.name()).isEqualTo(roomDTO.guest().name());
        });
    }
}
