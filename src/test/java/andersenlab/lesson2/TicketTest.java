package andersenlab.lesson2;

import com.andersenlab.lesson2.util.StadiumSector;
import com.andersenlab.lesson2.entity.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TicketTest {

    @Test
    public void testCreateTicketWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> Ticket.createTicket("12345", "ConcertHall", "456", System.currentTimeMillis() / 1000 + 3600, false, StadiumSector.A, 5.0, 100));
    }

    @Test
    public void testCreateTicketWithInvalidConcertHall() {
        assertThrows(IllegalArgumentException.class, () -> Ticket.createTicket("1234", "VeryLongConcertHallName", "456", System.currentTimeMillis() / 1000 + 3600, false, StadiumSector.A, 5.0, 100));
    }

    @Test
    public void testCreateTicketWithInvalidEventCode() {
        assertThrows(IllegalArgumentException.class, () -> Ticket.createTicket("1234", "ConcertHall", "12A", System.currentTimeMillis() / 1000 + 3600, false, StadiumSector.A, 5.0, 100));
    }

    @Test
    public void testCreateTicketWithInvalidEventTime() {
        assertThrows(IllegalArgumentException.class, () -> Ticket.createTicket("1234", "ConcertHall", "456", System.currentTimeMillis() / 1000 - 3600, false, StadiumSector.A, 5.0, 100));
    }
}
