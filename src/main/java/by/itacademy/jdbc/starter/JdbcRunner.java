package by.itacademy.jdbc.starter;

import by.itacademy.jdbc.starter.dao.FlightDao;
import by.itacademy.jdbc.starter.dao.TicketDao;
import by.itacademy.jdbc.starter.dao.filter.TicketFilter;
import by.itacademy.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        try (var connection = ConnectionManager.get()) {

            var flightDao = FlightDao.getInstance();
            System.out.println(flightDao.findById(1L));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        var ticketDao = TicketDao.getInstance();
//        System.out.println(ticketDao.findById(3L).get().getFlight());


    }

    private static void findTicket(Connection connection) {
        var ticketDao = TicketDao.getInstance();
        var filter = new TicketFilter(3, 0, null, "A1");
        System.out.println(ticketDao.findAll(filter));
    }

    /*  private static TicketDao getTicketDao() {
        var ticketDao = TicketDao.getInstance();

        Ticket ticket = new Ticket();
        ticket.setPassportNo("34242");
        ticket.setPassengerName("Andrei");
        ticket.setFlight(3L);
        ticket.setSeatNo("3B");
        ticket.setCost(BigDecimal.TEN);

        System.out.println(ticketDao.save(ticket));
        return ticketDao;
    } */

    private static List<Long> getTicketsByFlightId(Long flightId) {
        String sql = """
                select * from ticket
                where flight_id = ? """;
        List<Long> id = new ArrayList<>();
        try (var connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(sql);
            System.out.println(statement);
            statement.setLong(1, flightId);
            System.out.println(statement);
            var result = statement.executeQuery();
            System.out.println(statement);
            while (result.next()) {
                id.add(result.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }


    private static List<Long> getFlightByDates(LocalDateTime start, LocalDateTime end) {
        String sql = """
                select * from flight
                where departure_date::date between ? and ?
                 
                 """;
        List<Long> flightsId = new ArrayList<>();
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(sql)) {
            var metaData = connection.getMetaData();
            var catalogs = metaData.getCatalogs();
            while (catalogs.next()) {
                System.out.println(catalogs.getString(1));
            }
            statement.setFetchSize(2);
            statement.setMaxRows(2);

            System.out.println(statement);
            statement.setTimestamp(1, Timestamp.valueOf(start));
            System.out.println(statement);
            statement.setTimestamp(2, Timestamp.valueOf(end));
            System.out.println(statement);
            var result = statement.executeQuery();
            while (result.next()) {
                flightsId.add(result.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flightsId;
    }
}
