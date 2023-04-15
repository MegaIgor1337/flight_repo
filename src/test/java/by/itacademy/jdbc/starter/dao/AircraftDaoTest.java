package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.dao.filter.AircraftFilter;
import junit.framework.TestCase;

import java.util.List;
import java.util.Optional;

public class AircraftDaoTest extends TestCase {
    private static final AircraftDao aircraftDao = AircraftDao.getInstance();

    public void testUpdate() {
        Aircraft aircraft = new Aircraft(
                3L,
                "Аэробус A320-210"
        );
        assertTrue(aircraftDao.update(aircraft));
        if (aircraftDao.findById(3L).isPresent()) {
            assertEquals("Аэробус A320-210",
                    aircraftDao.findById(3L).get().getModel());
        }
    }

    public void testFindById() {
        Optional<Aircraft> aircraft = aircraftDao.findById(2L);
        aircraft.ifPresent(value -> assertEquals("Боинг 737-300", value.getModel()));
    }

    public void testFindAll() {
        List<Aircraft> aircrafts = aircraftDao.findAll();
        assertEquals("Суперджет-100", aircrafts.stream().
                sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId())).toList().get(3).getModel());
        assertEquals("Боинг 777-300", aircrafts.stream().
                sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId())).toList().get(0).getModel());
    }

    public void testTestFindAll() {
        AircraftFilter filter = new AircraftFilter(2, 0, "Боинг 777-300");
        List<Aircraft> aircrafts = aircraftDao.findAll(filter);
        assertEquals("Боинг 777-300", aircrafts.get(0).getModel());
    }

    public void testSave() {
        Aircraft aircraft = new Aircraft(null, "Су-47");
        Aircraft saveAircraft = aircraftDao.save(aircraft);
        assertEquals(Optional.of(aircraftDao.findAll().stream().
                        sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId())).toList().get(4).getId()),
                Optional.of(saveAircraft.getId()));
        assertEquals(5, aircraftDao.findAll().size());
    }

    public void testDelete() {
        assertTrue(aircraftDao.delete(aircraftDao.findAll().stream().
                sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId()))
                .toList().get(aircraftDao.findAll().size() - 1).getId()));
        assertEquals(4, aircraftDao.findAll().size());
    }
}