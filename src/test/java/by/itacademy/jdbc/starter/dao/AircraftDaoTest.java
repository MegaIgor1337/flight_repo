package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.service.AircraftService;
import junit.framework.TestCase;

import java.util.List;
import java.util.Optional;

public class AircraftDaoTest extends TestCase {
    private static final AircraftService aircraftService = AircraftService.getInstance();

    public void testUpdate() {
        Aircraft aircraft = new Aircraft(
                3L,
                "Аэробус A320-210"
        );
        assertTrue(aircraftService.update(aircraft));
        if (aircraftService.find(3L).isPresent()) {
            assertEquals("Аэробус A320-210",
                    aircraftService.find(3L).get().getModel());
        }
    }

    public void testFindById() {
        Optional<Aircraft> aircraft = aircraftService.find(2L);
        aircraft.ifPresent(value -> assertEquals("Боинг 737-300", value.getModel()));
    }

    public void testFindAll() {
        List<Aircraft> aircrafts = aircraftService.get();
        assertEquals("Суперджет-100", aircrafts.stream().
                sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId())).toList().get(3).getModel());
        assertEquals("Боинг 777-300", aircrafts.stream().
                sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId())).toList().get(0).getModel());
    }



    public void testSave() {
        Aircraft aircraft = new Aircraft(null, "Су-47");

        assertTrue(aircraftService.save(aircraft));
        assertEquals(5, aircraftService.get().size());
    }

    public void testDelete() {
        assertTrue(aircraftService.delete(aircraftService.get().stream().
                sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId()))
                .toList().get(aircraftService.get().size() - 1).getId()));
        assertEquals(4, aircraftService.get().size());
    }
}