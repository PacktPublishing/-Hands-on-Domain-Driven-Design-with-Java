package expert.os.books.ddd.chapter09.hotels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private static final Logger LOGGER = Logger.getLogger(HotelController.class.getName());

    private final RoomService roomService;

    @Autowired
    public HotelController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getRooms(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        LOGGER.info("Finding rooms page: " + page + " size: " + size);
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Room> rooms = roomService.findRooms(pageRequest);
        LOGGER.info("Found rooms: " + rooms.size());
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{number}")
    public ResponseEntity<Room> getReservation(@PathVariable Long number) {
        LOGGER.info("Finding reservation: " + number);
        Optional<Room> room = roomService.reservation(number);
        return room.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping
    public ResponseEntity<Room> checkIn(@RequestBody Room room) {
        LOGGER.info("Check in: " + room);
        Room checkedInRoom = roomService.checkIn(room);
        return ResponseEntity.ok(checkedInRoom);
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<Void> checkOut(@PathVariable Long number) {
        LOGGER.info("Check out: " + number);
        Optional<Room> room = roomService.reservation(number);
        if (room.isPresent()) {
            roomService.checkOut(room.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

