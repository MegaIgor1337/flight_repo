package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.airport.Airport;
import by.itacademy.jdbc.starter.dao.filter.AirportFilter;
import junit.framework.TestCase;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class AirportDaoTest extends TestCase {

    private static final AirportDao airportDao = AirportDao.getInstance();

    public void testUpdate() {
        Airport airport = new Airport(
                "MNK",
                "Беларусь",
                "Минск-2"
        );
        assertTrue(airportDao.update(airport));
        if (airportDao.findById("MNK").isPresent()) {
            assertEquals("Минск-2", airportDao.findById("MNK").get().getCity());
        }
    }

    public void testFindById() {
        Optional<Airport> airport = airportDao.findById("LDN");
        if (airport.isPresent()) {
            assertEquals("Англия", airport.get().getCountry());
            assertEquals("Лондон", airport.get().getCity());
        } else {
            fail();
        }
    }

    public void testFindAll() {
        List<Airport> airports = airportDao.findAll();
        assertEquals("MNK", airports.stream()
                .sorted(Comparator.comparing(Airport::getCode)).toList().get(2).getCode());
        assertEquals("Россия", airports.stream()
                .sorted(Comparator.comparing(Airport::getCode)).toList().get(3).getCountry());
    }

    public void testTestFindAll() {
        AirportFilter filter = new AirportFilter(5, 0, null, "Испания", null);
        List<Airport> airports = airportDao.findAll(filter);
        assertEquals("BSL", airports.get(0).getCode());
        assertEquals("Барселона", airports.get(0).getCity());
        assertEquals(1, airports.size());
    }


    public void testSave() {
        Airport airport = new Airport("PRS", "Франция", "Париж");
        Airport saveAirport = airportDao.save(airport);
        assertEquals(airport, saveAirport);
        assertEquals(5, airportDao.findAll().size());
    }

    public void testDelete() {
        assertTrue(airportDao.delete(airportDao.findAll().stream()
                .sorted(Comparator.comparing(Airport::getCode)).toList()
                .get(airportDao.findAll().size() - 1).getCode()));
    }
}