package expert.os.books.ddd.chapter07.hotels.nosql;

import com.mongodb.client.MongoClient;
import expert.os.books.ddd.chapter07.hotels.Hotel;
import expert.os.books.ddd.chapter07.hotels.Room;

import java.util.Optional;

public class HotelMongoDB implements Hotel {

    private final MongoClient mongoClient;

    private final NoSQLMapper mapper;

    public HotelMongoDB(MongoClient mongoClient, NoSQLMapper mapper) {
        this.mongoClient = mongoClient;
        this.mapper = mapper;
    }

    @Override
    public Room checkIn(Room room) {
        return null;
    }

    @Override
    public void checkOut(Room room) {

    }

    @Override
    public Optional<Room> reservation(String number) {
        return Optional.empty();
    }

    @Override
    public Long countBy() {
        return 0;
    }

    @Override
    public Optional<Room> findEmptyRoom() {
        return Optional.empty();
    }
}
