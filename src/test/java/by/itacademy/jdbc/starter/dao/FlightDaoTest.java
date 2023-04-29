package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.entity.airport.Airport;
import by.itacademy.jdbc.starter.entity.flight.Flight;
import by.itacademy.jdbc.starter.entity.flight.FlightStatus;
import by.itacademy.jdbc.starter.service.FlightService;
import junit.framework.TestCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FlightDaoTest extends TestCase {
    private static final FlightService flightService = FlightService.getInstance();

    public void testUpdate() {
        Flight flight = new Flight(
                4L,
                "BC2801",
                LocalDateTime.of(2020, 8, 1, 11, 0, 0),
                new Airport(
                        "LDN",
                        "Англия",
                        "Лондон"
                ),
                LocalDateTime.of(2020, 8, 1, 14, 20, 0),
                new Airport(
                        "MNK",
                        "Беларусь",
                        "Минск"
                ),
                new Aircraft(
                        2L,
                        "Боинг 737-300"
                ),
                FlightStatus.DEPARTED
        );
        assertTrue(flightService.update(flight));
        if (flightService.find(4L).isPresent()) {
            assertEquals(LocalDateTime.of(2020, 8, 1, 14, 20, 0),
                    flightService.find(4L).get().getArrivalDate());
        } else {
            fail();
        }
    }

    public void testFindById() {
        Optional<Flight> flight = flightService.find(1L);
        if (flight.isPresent()) {
            assertEquals("MN3002", flight.get().getFlightNo());
            assertEquals(Optional.of(1L), Optional.of(flight.get().getId()));
        } else {
            fail();
        }
    }

    public void testFindAll() {
        List<Flight> flights = flightService.get();
        assertEquals(Optional.of(1L), Optional.of(flights.stream().
                sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId())).
                toList().
                get(0).
                getId()));
        assertEquals(Optional.of("NK42K"), Optional.of(flights.stream().
                sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId())).
                toList().
                get(2).getFlightNo()));
        assertEquals(LocalDateTime.of(2020, 9, 19, 8, 55, 0),
                flights.stream().
                        sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId())).
                        toList().get(7).getDepartureDate());
    }

    public void testDelete() {
        assertTrue(flightService.delete(flightService.get().stream()
                .sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId()))
                .toList().get(flightService.get().size() - 1).getId()));
    }

    public void testSave() {
        Flight flight = new Flight(
                null,
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
        );

        assertTrue( flightService.save(flight));
    }

}