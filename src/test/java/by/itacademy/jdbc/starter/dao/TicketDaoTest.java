package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.entity.airport.Airport;
import by.itacademy.jdbc.starter.entity.flight.Flight;
import by.itacademy.jdbc.starter.entity.flight.FlightStatus;
import by.itacademy.jdbc.starter.entity.ticket.Ticket;
import by.itacademy.jdbc.starter.filter.TicketFilter;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class TicketDaoTest extends TestCase {
    private static final TicketDao ticketDao = TicketDao.getInstance();


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
                "B2  ",
                BigDecimal.valueOf(444.00)
        );
        ticketDao.update(ticket);
        BigDecimal bd = new BigDecimal("444.00").setScale(2, RoundingMode.HALF_UP);
        if (ticketDao.findById(15L).isPresent()) {
            assertEquals(bd, ticketDao.findById(15L).get().getCost());
        } else {
            fail();
        }
    }

    public void testFindById() {

        Optional<Ticket> findTicket = ticketDao.findById(21L);
        if (findTicket.isPresent()) {
            assertEquals("Анастасия Шепелева", findTicket.get().getPassengerName());
        } else {
            fail();
        }
    }

    public void testFindAll() {
        List<Ticket> tickets = ticketDao.findAll();
        assertEquals("112233", tickets.get(0).getPassportNo());
        assertEquals("Светлана Светикова", tickets.get(10).getPassengerName());
        assertEquals("D2  ", tickets.get(14).getSeatNo());
    }

    public void testTestFindAll() {
        TicketFilter filter = new TicketFilter(10, 0, null, "B2");
        List<Ticket> tickets = ticketDao.findAll(filter);
        assertEquals("SS988D", tickets.get(0).getPassportNo());
        assertEquals("Иван Розмаринов", tickets.get(1).getPassengerName());
        assertEquals(Optional.ofNullable(3L), Optional.ofNullable(tickets.stream().
                sorted(((o1, o2) -> Long.compare(o1.getId(), o2.getId()))).
                toList().
                get(2).getFlight().getId()));
        assertEquals(5, tickets.size());
    }

    public void testDelete() {

        assertTrue(ticketDao.delete(ticketDao.findAll().stream()
                .sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId()))
                .toList().get(ticketDao.findAll().size() - 1).getId()));
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
                "D2",
                BigDecimal.valueOf(294.00)
        );
        Ticket saveTicket = ticketDao.save(ticket);
        assertEquals(Optional.of(ticketDao.findAll().stream()
                .sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId()))
                .toList().get(ticketDao.findAll().size() - 1).getId()), Optional.of(saveTicket.getId()));
    }
}