package expert.os.books.ddd.chapter09.hotels;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    @BeforeEach
    void setUp() {
        roomRepository.deleteAll();
    }

    @Test
    void shouldSaveAndRetrieveRoomByNumber() {
        Room room = new Room();
        roomRepository.save(room);

        Optional<Room> retrievedRoom = roomRepository.findByNumber(room.getNumber());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(retrievedRoom).isPresent();
            softly.assertThat(retrievedRoom.orElseThrow().getNumber()).isEqualTo(room.getNumber());
        });
    }

    @Test
    void shouldSaveRoomWithGuest() {

        Guest guest = guestRepository.save(new Guest("123456789", "Jane Doe"));
        Room room = new Room();
        room.setGuest(guest);
        roomRepository.save(room);

        Optional<Room> retrievedRoom = roomRepository.findByNumber(room.getNumber());

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(retrievedRoom).isPresent();
            softly.assertThat(retrievedRoom.orElseThrow().getGuest()).isNotNull();
            softly.assertThat(retrievedRoom.orElseThrow().getGuest().getDocumentNumber()).isEqualTo("123456789");
        });
    }

    @Test
    void shouldFindEmptyRoomWhenGuestIsNull() {
        Room emptyRoom = new Room();
        roomRepository.save(emptyRoom);

        Guest guest = guestRepository.save(new Guest("987654321", "John Smith"));  // Save guest first
        Room occupiedRoom = new Room();
        occupiedRoom.setGuest(guest);
        roomRepository.save(occupiedRoom);

        Optional<Room> retrievedEmptyRoom = roomRepository.findByGuestIsNull();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(retrievedEmptyRoom).isPresent();
            softly.assertThat(retrievedEmptyRoom.orElseThrow().getGuest()).isNull();
            softly.assertThat(retrievedEmptyRoom.orElseThrow().getNumber()).isEqualTo(emptyRoom.getNumber());
        });
    }
}
