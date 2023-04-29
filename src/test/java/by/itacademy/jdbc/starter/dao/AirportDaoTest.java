package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.airport.Airport;
import by.itacademy.jdbc.starter.service.AirportService;
import junit.framework.TestCase;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class AirportDaoTest extends TestCase {

    private static final AirportService airportService = AirportService.getInstance();

    public void testUpdate() {
        Airport airport = new Airport(
                "MNK",
                "Беларусь",
                "Минск-2"
        );
        assertTrue(airportService.update(airport));
        if (airportService.find("MNK").isPresent()) {
            assertEquals("Минск-2", airportService.find("MNK").get().getCity());
        }
    }

    public void testFindById() {
        Optional<Airport> airport = airportService.find("LDN");
        if (airport.isPresent()) {
            assertEquals("Англия", airport.get().getCountry());
            assertEquals("Лондон", airport.get().getCity());
        } else {
            fail();
        }
    }

    public void testFindAll() {
        List<Airport> airports = airportService.get();
        assertEquals("MNK", airports.stream()
                .sorted(Comparator.comparing(Airport::getCode)).toList().get(2).getCode());
        assertEquals("Россия", airports.stream()
                .sorted(Comparator.comparing(Airport::getCode)).toList().get(3).getCountry());
    }




    public void testSave() {
        Airport airport = new Airport("PRS", "Франция", "Париж");
        assertTrue(airportService.save(airport));
    }

    public void testDelete() {
        assertTrue(airportService.delete(airportService.get().stream()
                .sorted(Comparator.comparing(Airport::getCode)).toList()
                .get(airportService.get().size() - 1).getCode()));
    }
}