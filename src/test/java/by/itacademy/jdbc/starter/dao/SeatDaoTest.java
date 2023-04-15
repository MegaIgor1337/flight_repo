package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.entity.seat.Seat;
import by.itacademy.jdbc.starter.entity.seat.SeatKey;
import by.itacademy.jdbc.starter.dao.filter.SeatFilter;
import junit.framework.TestCase;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SeatDaoTest extends TestCase {
    private static final SeatDao seatDao = SeatDao.getInstance();

    public void testUpdate() {
        Random rand = new Random();

        Seat seat = new Seat(new SeatKey(new Aircraft(4L, "Суперджет-100"),
                seatDao.findAll().stream().sorted(
                                (o1, o2) -> Long.compare(o1.getSeatKey().getAircraft().getId(),
                                        o2.getSeatKey().getAircraft().getId())).toList().
                        get(seatDao.findAll().size() - 1).getSeatKey().getSeatNo(),
                new Aircraft(4L, "Суперджет-100"), "D" + (rand.nextInt(98) + 3)));
        assertTrue(seatDao.update(seat));
    }

    public void testFindById() {
        Optional<Seat> seat = seatDao.findById(new SeatKey(
                new Aircraft(4L, "Суперджет-100"), "B2"));
        seat.ifPresent(value -> assertEquals(
                "Суперджет-100", value.getSeatKey().getAircraft().getModel()));
    }

    public void testFindAll() {
        List<Seat> seats = seatDao.findAll();
        assertEquals(32, seats.size());
        assertEquals("D2  ", seats.stream().sorted(
                        (o1, o2) -> Long.compare(o1.getSeatKey().getAircraft().getId(),
                                o2.getSeatKey().getAircraft().getId())).
                toList().get(23).getSeatKey().getSeatNo());
    }

    public void testTestFindAll() {
        List<Seat> seats = seatDao.findAll(
                new SeatFilter(1, 0, 3L, "D2"));
        assertEquals(Optional.of(3L),
                Optional.of(seats.get(0).getSeatKey().getAircraft().getId()));
    }

    public void testSave() {
        Seat seat = new Seat(new SeatKey(
                new Aircraft(4L, "Суперджет-100"), "D5"));
        Seat saveSeat = seatDao.save(seat);
        assertEquals(seat, saveSeat);
        assertEquals(33, seatDao.findAll().size());
    }

    public void testDelete() {
        assertTrue(seatDao.delete(new SeatKey(
                new Aircraft(4L, "Суперджет-100"), "D5")));
        assertEquals(32, seatDao.findAll().size());
    }


}