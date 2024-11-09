package expert.os.books.ddd.chapter07.hotels.jpa;

import expert.os.books.ddd.chapter07.hotels.Hotel;
import expert.os.books.ddd.chapter07.hotels.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class HotelJPA implements Hotel {

    private final SessionFactory sessionFactory;

    private final JPAMapper mapper;

    public HotelJPA(SessionFactory sessionFactory, JPAMapper mapper) {
        this.sessionFactory = sessionFactory;
        this.mapper = mapper;
    }

    @Override
    public Room checkIn(Room room) {
        try (var session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            RoomJPA roomJPA = session.get(RoomJPA.class, room.getNumber());
            if (roomJPA != null) {
                // Map the guest from the domain object to the JPA entity
                GuestJPA guestJPA = mapper.toEntity(room.getGuest());
                roomJPA.setGuest(guestJPA);
                session.saveOrUpdate(roomJPA);
                transaction.commit();
                return mapper.toDomain(roomJPA);  // Return the updated room with the guest assigned
            }
            transaction.rollback();
            throw new IllegalArgumentException("Room not found");
        }
    }

    @Override
    public void checkOut(Room room) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            RoomJPA roomJPA = session.get(RoomJPA.class, room.getNumber());
            if (roomJPA != null) {
                roomJPA.cleanRoom();
                session.saveOrUpdate(roomJPA);
                transaction.commit();
            } else {
                transaction.rollback();
                throw new IllegalArgumentException("Room not found");
            }
        }
    }

    @Override
    public Optional<Room> reservation(String number) {
        try (Session session = sessionFactory.openSession()) {
            RoomJPA roomJPA = session.createQuery("from RoomJPA where number = :number", RoomJPA.class)
                    .setParameter("number", Long.parseLong(number))
                    .uniqueResult();
            return Optional.ofNullable(roomJPA).map(mapper::toDomain);
        }
    }

    @Override
    public Long countBy() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(r) from RoomJPA r", Long.class).uniqueResult();
        }
    }

    @Override
    public Optional<Room> findEmptyRoom() {
        try (Session session = sessionFactory.openSession()) {
            RoomJPA emptyRoom = session.createQuery("from RoomJPA where guest is null", RoomJPA.class)
                    .setMaxResults(1)
                    .uniqueResult();
            return Optional.ofNullable(emptyRoom).map(mapper::toDomain);
        }
    }
}
