package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.airport.Airport;
import by.itacademy.jdbc.starter.exceptions.DaoException;
import by.itacademy.jdbc.starter.filter.AirportFilter;
import by.itacademy.jdbc.starter.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AirportDao implements Dao<String, Airport, AirportFilter> {

    private static final AirportDao INSTANCE = new AirportDao();

    private static String FIND_BY_CODE_SQL = """
            select code, country, city 
            from airport
            where code = ?
            """;

    private static String FIND_ALL_SQL = """
            select code, country, city
            FROM airport  
            """;


    private static String UPDATE_SQL = """
            update airport
            set country = ?,
            city = ?        
            where code = ?
            """;

    private static String DELETE_SQL = """
            delete from airport
            where code = ?
            """;

    private static String SAVE_SQL = """
            insert into airport (code, country, city)
            values (?, ?, ?)
            """;

    private Airport buildAirport(ResultSet result) throws SQLException {
        return new Airport(
                result.getString("code"),
                result.getString("country"),
                result.getString("city")
        );
    }

    @Override
    public boolean update(Airport airport) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, airport.getCountry());
            statement.setString(2, airport.getCity());
            statement.setString(3, airport.getCode());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Airport> findById(String code) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_CODE_SQL)) {
            Airport airport = null;
            statement.setString(1, code);
            var result = statement.executeQuery();
            if (result.next())
                airport = buildAirport(result);
            return Optional.ofNullable(airport);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Airport> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Airport> airports = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                airports.add(buildAirport(result));
            return airports;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Airport> findAll(AirportFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.country() != null) {
            whereSql.add("country = ?");
            parameters.add(filter.country());
        }
        if (filter.city() != null) {
            whereSql.add("city = ?");
            parameters.add(filter.city());
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
            List<Airport> airports = new ArrayList<>();
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            var result = statement.executeQuery();
            while (result.next())
                airports.add(buildAirport(result));
            return airports;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(String code) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setString(1, code);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Airport save(Airport airport) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL)) {
            statement.setString(1, airport.getCode());
            statement.setString(2, airport.getCountry());
            statement.setString(3, airport.getCity());
            statement.executeUpdate();
            return airport;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private AirportDao() {
    }

    public static AirportDao getInstance() {
        return INSTANCE;
    }
}
