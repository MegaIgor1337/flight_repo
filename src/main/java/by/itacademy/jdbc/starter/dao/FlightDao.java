package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.filter.FlightFilter;
import by.itacademy.jdbc.starter.entity.flight.Flight;
import by.itacademy.jdbc.starter.entity.flight.FlightStatus;
import by.itacademy.jdbc.starter.exceptions.DaoException;
import by.itacademy.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightDao implements Dao<Long, Flight, FlightFilter> {
    private static final FlightDao INSTANCE = new FlightDao();
    private static final AirportDao airportDao = AirportDao.getInstance();
    private static final AircraftDao aircraftDao = AircraftDao.getInstance();

    private static String FIND_BY_ID_SQL = """
            SELECT id, flight_no, departure_date, departure_airport_code,
            arrival_date, arrival_airport_code, aircraft_id, status
            FROM flight
            WHERE id = ?
            """;
    private static String FIND_ALL_SQL = """
            SELECT id, flight_no, departure_date, departure_airport_code, 
            arrival_date, arrival_airport_code, aircraft_id, status
            FROM flight
                      
            """;


    private static String UPDATE_SQL = """
            update flight
            set flight_no = ?,
            departure_date = ?,
            departure_airport_code = ?,
            arrival_date = ?,
            arrival_airport_code = ?,
            aircraft_id = ?,
            status = ?
            where id = ?
            """;

    private static String DELETE_SQL = """
            delete from flight
            where id = ?
            """;

    private static String SAVE_SQL = """
            insert into flight (flight_no,
             departure_date, departure_airport_code,
            arrival_date,
            arrival_airport_code,
            aircraft_id,
            status)
            values (?, ?, ?, ?, ?, ?, ?)
            """;


    public boolean update(Flight flight) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, flight.getFlightNo());
            statement.setTimestamp(2, Timestamp.valueOf(flight.getDepartureDate()));
            statement.setString(3, flight.getDepartureAirport().getCode());
            statement.setTimestamp(4, Timestamp.valueOf(flight.getArrivalDate()));
            statement.setString(5, flight.getArrivalAirport().getCode());
            statement.setLong(6, flight.getAircraft().getId());
            statement.setString(7, flight.getStatus().toString());
            statement.setLong(8, flight.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            Flight flight = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                flight = buildFlight(result);
            return Optional.ofNullable(flight);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Flight buildFlight(ResultSet result) throws SQLException {
        return new Flight(
                result.getLong("id"),
                result.getString("flight_no"),
                result.getTimestamp("departure_date").toLocalDateTime(),
                airportDao.findById(result.getString("departure_airport_code")).orElse(null),
                result.getTimestamp("arrival_date").toLocalDateTime(),
                airportDao.findById(result.getString("arrival_airport_code")).orElse(null),
                aircraftDao.findById(result.getLong("aircraft_id")).orElse(null),
                FlightStatus.valueOf(result.getString("status"))
        );
    }

    @Override
    public List<Flight> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Flight> flights = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                flights.add(buildFlight(result));
            return flights;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Flight save(Flight flight) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, flight.getFlightNo());
            statement.setTimestamp(2, Timestamp.valueOf(flight.getDepartureDate()));
            statement.setString(3, flight.getDepartureAirport().getCode());
            statement.setTimestamp(4, Timestamp.valueOf(flight.getDepartureDate()));
            statement.setString(5, flight.getArrivalAirport().getCode());
            statement.setLong(6, flight.getAircraft().getId());
            statement.setString(7, flight.getStatus().toString());

            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                flight.setId(generatedKeys.getLong("id"));
            return flight;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Flight> findAll(FlightFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.flightNo() != null) {
            whereSql.add("flight_no like ?");
            parameters.add("%" + filter.flightNo() + "%");
        }
        if (filter.departureDate() != null) {
            whereSql.add("departure_date = ?");
            parameters.add(filter.departureDate());
        }
        if (filter.departureAirportCode() != null) {
            whereSql.add("departure_airport_code = ?");
            parameters.add(filter.departureAirportCode());
        }
        if (filter.arrivalDate() != null) {
            whereSql.add("arrival_date = ?");
            parameters.add(filter.arrivalDate());
        }
        if (filter.arrivalAirportCode() != null) {
            whereSql.add("arrival_airport_code = ?");
            parameters.add(filter.arrivalAirportCode());
        }
        if (filter.aircraftId() != null) {
            whereSql.add("aircraft_id");
            parameters.add(filter.aircraftId());
        }
        if (filter.status() != null) {
            whereSql.add("status = ?");
            parameters.add(filter.status());
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
            List<Flight> flights = new ArrayList<>();
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            var result = statement.executeQuery();
            while (result.next())
                flights.add(buildFlight(result));

            return flights;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private FlightDao() {
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }

}
