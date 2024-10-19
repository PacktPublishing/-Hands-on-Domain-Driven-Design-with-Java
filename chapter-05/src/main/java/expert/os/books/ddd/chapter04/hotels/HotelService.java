package expert.os.books.ddd.chapter04.hotels;

import org.jmolecules.ddd.annotation.Service;

import java.util.Optional;

@Service
public class HotelService {

        private final Hotel hotel;

        public HotelService(Hotel hotel) {
            this.hotel = hotel;
        }

        public Room checkIn(Room room) {
            return hotel.checkIn(room);
        }

        public void checkOut(Room room) {
            hotel.checkOut(room);
        }

        public Optional<Room> reservation(String number) {
            return hotel.reservation(number);
        }
}
