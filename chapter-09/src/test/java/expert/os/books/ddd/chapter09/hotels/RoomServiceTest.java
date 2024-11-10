package expert.os.books.ddd.chapter09.hotels;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    @BeforeEach
    void setUp() {
        roomRepository.deleteAll();
        guestRepository.deleteAll();
    }

    @Test
    void shouldCheckInWhenGuestAlreadyExists() {
        Guest existingGuest = guestRepository.save(new Guest("123456789", "John Doe"));
        Room room = roomRepository.save(new Room(6L, existingGuest));

        room.setGuest(existingGuest);
        Room checkedInRoom = roomService.checkIn(room);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(checkedInRoom).isNotNull();
            softly.assertThat(checkedInRoom.getGuest()).isEqualTo(existingGuest);
        });
    }

    @Test
    void shouldCheckInWhenGuestDoesNotExist() {
        Guest newGuest = new Guest("987654321", "Jane Doe");
        Room room =  roomRepository.save(new Room(2L, null));
        room.setGuest(newGuest);
        Room checkedInRoom = roomService.checkIn(room);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(checkedInRoom).isNotNull();
            softly.assertThat(checkedInRoom.getGuest().getDocumentNumber()).isEqualTo("987654321");
            softly.assertThat(checkedInRoom.getGuest().getName()).isEqualTo("Jane Doe");
            softly.assertThat(guestRepository.findByDocumentNumber("987654321")).isPresent();
        });
    }

    @Test
    void shouldThrowExceptionWhenRoomNotFoundForCheckIn() {
        Room room = new Room();
        room.setNumber(3L);

        assertThatThrownBy(() -> roomService.checkIn(room))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Room not found");
    }

    @Test
    void shouldCheckOutWhenRoomExists() {
        Guest guest = guestRepository.save(new Guest("111222333", "Mike Smith"));
        Room room = roomRepository.save(new Room(4L, guest));

        roomService.checkOut(room);

        Room updatedRoom = roomRepository.findByNumber(room.getNumber()).orElseThrow();
        assertThat(updatedRoom.getGuest()).isNull();
    }

    @Test
    void shouldThrowExceptionWhenRoomNotFoundForCheckOut() {
        Room room = new Room();
        room.setNumber(5L);

        assertThatThrownBy(() -> roomService.checkOut(room))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Room not found");
    }

    @Test
    void shouldRetrieveReservationWhenRoomExists() {

        Room room = roomRepository.save(new Room(6L, null));

        Optional<Room> retrievedRoom = roomService.reservation(room.getNumber());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(retrievedRoom).isPresent();
            softly.assertThat(retrievedRoom.get().getNumber()).isEqualTo(room.getNumber());
        });
    }

    @Test
    void shouldReturnEmptyReservationWhenRoomDoesNotExist() {
        Optional<Room> retrievedRoom = roomService.reservation(7L);

        assertThat(retrievedRoom).isNotPresent();
    }

    @Test
    void shouldCountRooms() {
        roomRepository.save(new Room());
        roomRepository.save(new Room());

        Long roomCount = roomService.countBy();

        assertThat(roomCount).isEqualTo(2L);
    }

    @Test
    void shouldFindEmptyRoomWhenAvailable() {
        Room emptyRoom = new Room();
        roomRepository.save(emptyRoom);

        Optional<Room> retrievedEmptyRoom = roomService.findEmptyRoom();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(retrievedEmptyRoom).isPresent();
            softly.assertThat(retrievedEmptyRoom.get().getGuest()).isNull();
        });
    }

    @Test
    void shouldReturnEmptyWhenNoEmptyRoomIsAvailable() {
        Guest guest = guestRepository.save(new Guest("123456789", "Occupied Guest"));
        Room room = new Room();
        room.setGuest(guest);
        roomRepository.save(room);

        Optional<Room> retrievedEmptyRoom = roomService.findEmptyRoom();

        assertThat(retrievedEmptyRoom).isNotPresent();
    }
}
