package expert.os.books.ddd.chapter09.hotels;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    public RoomService(RoomRepository roomRepository, GuestRepository guestRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }

    public List<Room> findRooms(PageRequest pageRequest) {
        Page<Room> page = roomRepository.findAll(pageRequest);
        return page.getContent();
    }

    @Transactional
    public Room checkIn(Room room) {
        Room roomEntity = roomRepository.findByNumber(room.getNumber())
                .orElseThrow(() -> new EntityNotFoundException("Room not found, the room number is " + room.getNumber()));

        Guest guest = guestRepository.findByDocumentNumber(room.getGuest().getDocumentNumber())
                .orElseGet(() -> guestRepository.save(room.getGuest()));

        roomEntity.setGuest(guest);
        return roomRepository.save(roomEntity);
    }

    @Transactional
    public void checkOut(Room room) {
        Room roomEntity = roomRepository.findByNumber(room.getNumber())
                .orElseThrow(() -> new EntityNotFoundException("Room not found, the room number is " + room.getNumber()));

        roomEntity.setGuest(null);
        roomRepository.save(roomEntity);
    }

    public Optional<Room> reservation(Long number) {
        return roomRepository.findByNumber(number);
    }

    public Long countBy() {
        return roomRepository.count();
    }

    public Optional<Room> findEmptyRoom() {
        return roomRepository.findByGuestIsNull();
    }


}
