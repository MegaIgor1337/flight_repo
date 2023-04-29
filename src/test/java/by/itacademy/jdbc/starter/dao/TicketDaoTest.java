package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.entity.airport.Airport;
import by.itacademy.jdbc.starter.entity.flight.Flight;
import by.itacademy.jdbc.starter.entity.flight.FlightStatus;
import by.itacademy.jdbc.starter.entity.seat.Seat;
import by.itacademy.jdbc.starter.entity.seat.SeatKey;
import by.itacademy.jdbc.starter.entity.ticket.Ticket;
import by.itacademy.jdbc.starter.service.TicketService;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
public class TicketDaoTest extends TestCase {
    private static final TicketService ticketService = TicketService.getInstance();


    public void testUpdate() {
        Ticket ticket = new Ticket(
                15L,
                "DL123S",
                "Игорь Беркутов",
                new Flight(
                        3L,
                        "NK42K",
                        LocalDateTime.of(2020, 7, 28, 23, 25, 0),
                        new Airport(
                                "MNK",
                                "Беларусь",
                                "Минск"
                        ),
                        LocalDateTime.of(2020, 7, 29, 2, 43, 0),
                        new Airport(
                                "LDN",
                                "Англия",
                                "Лондон"
                        ),
                        new Aircraft(
                                2L,
                                "Боинг 737-300"
                        ),
                        FlightStatus.ARRIVED),

                                "D2",


                BigDecimal.valueOf(444.00)
        );
        ticketService.update(ticket);
        BigDecimal bd = new BigDecimal("444.00").setScale(2, RoundingMode.HALF_UP);
        if (ticketService.find(15L).isPresent()) {
            assertEquals(bd, ticketService.find(15L).get().getCost());
        } else {
            fail();
        }
    }

    public void testFindById() {

        Optional<Ticket> findTicket = ticketService.find(21L);
        if (findTicket.isPresent()) {
            assertEquals("Анастасия Шепелева", findTicket.get().getPassengerName());
        } else {
            fail();
        }
    }

    public void testFindAll() {
        List<Ticket> tickets = ticketService.get();
        assertEquals("112233", tickets.get(0).getPassportNo());
        assertEquals("Светлана Светикова", tickets.get(10).getPassengerName());
    }

    public void testDelete() {

        assertTrue(ticketService.delete(ticketService.get().stream()
                .sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId()))
                .toList().get(ticketService.get().size() - 1).getId()));
    }

    public void testSave() {
        Ticket ticket = new Ticket(
                null,
                "123951",
                "Полина Зверева",
                new Flight(
                        9L,
                        "QS8712",
                        LocalDateTime.of(2020, 12, 18, 3, 35, 0),
                        new Airport(
                                "MNK",
                                "Беларусь",
                                "Минск"
                        ),
                        LocalDateTime.of(2020, 12, 18, 6, 46, 0),
                        new Airport(
                                "LDN",
                                "Англия",
                                "Лондон"
                        ),
                        new Aircraft(
                                2L,
                                "Боинг 737-300"
                        ),
                        FlightStatus.ARRIVED
                ),

                                "D7",
                BigDecimal.valueOf(294.00)
        );
        assertTrue(ticketService.save(ticket));
    }

//    @Test
//    public void textLog() {
//           log.info("lkdfdlfkdlfd");
//    }
}