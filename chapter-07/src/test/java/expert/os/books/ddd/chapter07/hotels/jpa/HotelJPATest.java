package expert.os.books.ddd.chapter07.hotels.jpa;

import expert.os.books.ddd.chapter07.hotels.Hotel;
import expert.os.books.ddd.chapter07.hotels.Room;
import expert.os.books.ddd.chapter07.hotels.Guest;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.assertj.core.api.SoftAssertions;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class HotelJPATest {

    private static SessionFactory sessionFactory;
    private HotelJPA hotelJPA;
    private JPAMapper mapper;

    @BeforeAll
    static void setUpClass() {

        var registry = new StandardServiceRegistryBuilder().build();
        sessionFactory = new MetadataSources(registry)
                .addAnnotatedClass(GuestJPA.class)
                .addAnnotatedClass(RoomJPA.class)
                .buildMetadata()
                .buildSessionFactory();
    }

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(JPAMapper.class);
        hotelJPA = new HotelJPA(sessionFactory, mapper);

        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("INSERT INTO RoomJPA (number, guest_id) VALUES (101, NULL)").executeUpdate();
            session.createNativeQuery("INSERT INTO GuestJPA (id, documentNumber, name) VALUES (1, '12345', 'John Doe')").executeUpdate();
            session.getTransaction().commit();
        }
    }


    @AfterEach
    void tearDown() {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM RoomJPA").executeUpdate();
            session.createNativeQuery("DELETE FROM GuestJPA").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @AfterAll
    static void tearDownClass() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    void shouldCheckInGuestToRoom() {
        Guest guest = new Guest("12345", "John Doe");
        Room room = new Room(101, guest);

        Room checkedInRoom = hotelJPA.checkIn(room);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(checkedInRoom).isNotNull();
        softly.assertThat(checkedInRoom.getGuest().documentNumber()).isEqualTo(guest.documentNumber());
        softly.assertAll();
    }

    @Test
    void shouldCheckOutGuestFromRoom() {
        Guest guest = new Guest("12345", "John Doe");
        Room room = new Room(101, guest);

        hotelJPA.checkOut(room);

        Optional<Room> result = hotelJPA.reservation("101");

        SoftAssertions.assertSoftly(softly ->{
            softly.assertThat(result).isPresent();
            softly.assertThat(result.get().getGuest()).isNull();
        });
    }

    @Test
    void shouldFindEmptyRoom() {
        Optional<Room> emptyRoom = hotelJPA.findEmptyRoom();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(emptyRoom).isPresent();
            softly.assertThat(emptyRoom.get().getNumber()).isEqualTo(101);
        });
    }

    @Test
    void shouldCountRooms() {
        long roomCount = hotelJPA.countBy();
        assertThat(roomCount).isEqualTo(1);
    }
}
