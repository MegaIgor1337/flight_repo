package by.itacademy.jdbc.starter.filter;

public record TicketFilter(int limit,
                           int offset,
                           String passengerName,
                           String seatNo) {

}
