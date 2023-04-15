package by.itacademy.jdbc.starter.dao;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.exceptions.DaoException;
import by.itacademy.jdbc.starter.dao.filter.AircraftFilter;
import by.itacademy.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AircraftDao implements Dao<Long, Aircraft>{

    private static final AircraftDao INSTANCE = new AircraftDao();

    private static String FIND_BY_ID_SQL = """
            select id, model
            FROM  aircraft
            WHERE id = ?
            """;
    private static String FIND_ALL_SQL = """
            select id, model
            FROM aircraft  
            """;


    private static String UPDATE_SQL = """
            update aircraft
            set model = ?        
            where id = ?
            """;

    private static String DELETE_SQL = """
            delete from aircraft
            where id = ?
            """;

    private static String SAVE_SQL = """
            insert into aircraft (model)
            values (?)
            """;

    private Aircraft buildAircraft(ResultSet result) throws SQLException {
        return new Aircraft(
                result.getLong("id"),
                result.getString("model")
        );
    }

    @Override
    public boolean update(Aircraft aircraft) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, aircraft.getModel());
            statement.setLong(2, aircraft.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Aircraft> findById(Long id) {
        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            Aircraft aircraft = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                aircraft = buildAircraft(result);
            return Optional.ofNullable(aircraft);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Aircraft> findAll() {
        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Aircraft> aircrafts = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                aircrafts.add(buildAircraft(result));
            return aircrafts;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public List<Aircraft> findAll(AircraftFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.model() != null) {
            whereSql.add("model = ?");
            parameters.add(filter.model());
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
            List<Aircraft> aircrafts = new ArrayList<>();
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            var result = statement.executeQuery();
            while (result.next())
                aircrafts.add(buildAircraft(result));
            return aircrafts;
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
    public Aircraft save(Aircraft aircraft) {
        try (var connection = ConnectionManager.get();
                var statement = connection
                .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, aircraft.getModel());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                aircraft.setId(generatedKeys.getLong("id"));
            return aircraft;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public static AircraftDao getInstance() {
        return INSTANCE;
    }
}
