package expert.os.books.ddd.chapter09.hotels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
    void shouldReturnPagedRooms() throws Exception {
        Room room1 = roomRepository.save(new Room());
        Room room2 = roomRepository.save(new Room());

        mockMvc.perform(get("/hotels?page=0&size=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))) // Only 1 room per page
                .andExpect(jsonPath("$[0].number", is(room1.getNumber().intValue())));
    }

    @Test
    void shouldReturnRoomByNumber() throws Exception {
        Room room = roomRepository.save(new Room());

        mockMvc.perform(get("/hotels/" + room.getNumber())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(room.getNumber().intValue())));
    }

    @Test
    void shouldReturnNotFoundForNonExistentRoom() throws Exception {
        mockMvc.perform(get("/hotels/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCheckInGuest() throws Exception {
        Room room = roomRepository.save(new Room());

        String checkInJson = String.format("""
                {
                    "number": %d,
                    "guest": {
                        "documentNumber": "123456789",
                        "name": "John Doe"
                    }
                }
                """, room.getNumber());

        mockMvc.perform(put("/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(checkInJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(room.getNumber().intValue())))
                .andExpect(jsonPath("$.guest.documentNumber", is("123456789")))
                .andExpect(jsonPath("$.guest.name", is("John Doe")));
    }

    @Test
    void shouldCheckOutGuest() throws Exception {
        Guest guest = guestRepository.save(new Guest("987654321", "Jane Doe"));
        Room room = new Room();
        room.setGuest(guest);
        room = roomRepository.save(room);

        mockMvc.perform(delete("/hotels/" + room.getNumber())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/hotels/" + room.getNumber())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guest").doesNotExist());
    }

    @Test
    void shouldReturn404WhenRoomNotFound() throws Exception {
        mockMvc.perform(get("/hotels/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

