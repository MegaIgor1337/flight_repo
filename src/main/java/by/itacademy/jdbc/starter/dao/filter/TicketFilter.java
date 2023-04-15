package by.itacademy.jdbc.starter.dao.filter;

public record TicketFilter(int limit,
                           int offset,
                           String passengerName,
                           String seatNo) {

}
