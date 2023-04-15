package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.seat.Seat;
import by.itacademy.jdbc.starter.entity.seat.SeatKey;
import by.itacademy.jdbc.starter.exceptions.DaoException;
import by.itacademy.jdbc.starter.dao.filter.SeatFilter;
import by.itacademy.jdbc.starter.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SeatDao implements Dao<SeatKey, Seat> {

    private static final AircraftDao aircraftDao = AircraftDao.getInstance();

    private static final SeatDao INSTANCE = new SeatDao();

    private static String FIND_BY_ID_SQL = """
            select aircraft_id, seat_no
            FROM  seat
            WHERE aircraft_id = ? and seat_no = ?
            """;
    private static String FIND_ALL_SQL = """
            select aircraft_id, seat_no
            FROM seat  
            """;


    private static String UPDATE_SQL = """
            update seat
            set aircraft_id = ?,
            seat_no = ?
            where aircraft_id = ? and seat_no = ?

                        
            """;

    private static String DELETE_SQL = """
            delete from seat
            where aircraft_id = ? and seat_no = ?
            """;

    private static String SAVE_SQL = """
            insert into seat (aircraft_id, seat_no)
            values (?, ?)
            """;

    private Seat buildSeat(ResultSet result) throws SQLException {
        return new Seat(
                new SeatKey(aircraftDao.findById(
                        result.getLong("aircraft_id")).orElse(null),
                        result.getString("seat_no"))
        );
    }

    @Override
    public boolean update(Seat seat) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setLong(1, seat.getSeatKey().getNewAircraft().getId());
            statement.setString(2, seat.getSeatKey().getNewSeatNo());
            statement.setLong(3, seat.getSeatKey().getAircraft().getId());
            statement.setString(4, seat.getSeatKey().getSeatNo());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Seat> findById(SeatKey seatKey) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            Seat seat = null;
            statement.setLong(1, seatKey.getAircraft().getId());
            statement.setString(2, seatKey.getSeatNo());
            var result = statement.executeQuery();
            if (result.next())
                seat = buildSeat(result);
            return Optional.ofNullable(seat);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public List<Seat> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Seat> seats = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                seats.add(buildSeat(result));
            return seats;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Seat> findAll(SeatFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.aircraftId() != null) {
            whereSql.add("aircraft_id = ?");
            parameters.add(filter.aircraftId());
        }
        if (filter.seatNo() != null) {
            whereSql.add("seat_no = ?");
            parameters.add(filter.seatNo());
        }
        parameters.add(filter.limit());
        parameters.add(filter.offset());

        var where = whereSql.stream().collect(Collectors.joining(
                " AND ",
                parameters.size() > 2 ? " WHERE " : " ",
                " LIMIT ? OFFSET ?"
        ));
        String sql = FIND_ALL_SQL + where;

        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(sql)) {
            List<Seat> seats = new ArrayList<>();
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            var result = statement.executeQuery();
            while (result.next())
                seats.add(buildSeat(result));
            return seats;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(SeatKey seatKey) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, seatKey.getAircraft().getId());
            statement.setString(2, seatKey.getSeatNo());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Seat save(Seat seat) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL)) {
            statement.setLong(1, seat.getSeatKey().getAircraft().getId());
            statement.setString(2, seat.getSeatKey().getSeatNo());
            statement.executeUpdate();
            return seat;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private SeatDao() {

    }

    public static SeatDao getInstance() {
        return INSTANCE;
    }
}
