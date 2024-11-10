package expert.os.books.ddd.chapter09.hotels;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    @BeforeEach
    void setUp() {
        guestRepository.deleteAll();
    }

    @Test
    void shouldSaveAndRetrieveGuestByDocumentNumber() {
        Guest guest = new Guest("123456789", "John Doe");
        guestRepository.save(guest);

        Optional<Guest> retrievedGuest = guestRepository.findByDocumentNumber("123456789");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(retrievedGuest).isPresent();
            softly.assertThat(retrievedGuest.orElseThrow().getName()).isEqualTo("John Doe");
            softly.assertThat(retrievedGuest.orElseThrow().getDocumentNumber()).isEqualTo("123456789");
        });
    }

    @Test
    void shouldReturnEmptyWhenGuestNotFoundByDocumentNumber() {
        Optional<Guest> retrievedGuest = guestRepository.findByDocumentNumber("999999999");
        assertThat(retrievedGuest).isNotPresent();
    }
}
