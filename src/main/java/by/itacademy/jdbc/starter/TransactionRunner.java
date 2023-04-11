package by.itacademy.jdbc.starter;

import by.itacademy.jdbc.starter.util.ConnectionManager;
import by.itacademy.jdbc.starter.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRunner {
    public static void main(String[] args) throws SQLException {
        Long flight_id = 8L;
        String deleteFlightSql = """
                delete from flight
                where id = ?
                """;
        String deleteTicketSql = """
                delete from ticket
                where flight_id = ?
                """;
        Connection connection = null;
        PreparedStatement deleteFlightStatement = null;
        PreparedStatement deleteTicketStatement = null;
        try {
            connection = ConnectionManager.get();
            deleteFlightStatement = connection.prepareStatement(deleteFlightSql);
            deleteTicketStatement = connection.prepareStatement(deleteTicketSql);
            connection.setAutoCommit(false);
            deleteTicketStatement.setLong(1, flight_id);
            deleteTicketStatement.executeUpdate();
            if (true) {
                throw new RuntimeException();
            }
            deleteFlightStatement.setLong(1, flight_id);
            deleteFlightStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
                throw e;
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteFlightStatement != null) {
                deleteFlightStatement.close();
            }
            if (deleteTicketStatement != null) {
                deleteTicketStatement.close();
            }
        }
    }
}
