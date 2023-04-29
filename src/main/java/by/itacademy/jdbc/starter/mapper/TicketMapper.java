package by.itacademy.jdbc.starter.mapper;

import by.itacademy.jdbc.starter.dto.FlightDto;
import by.itacademy.jdbc.starter.dto.TicketDto;
import by.itacademy.jdbc.starter.entity.ticket.Ticket;

public class TicketMapper implements Mapper<Ticket, TicketDto> {
    private static final TicketMapper INSTANCE = new TicketMapper();
    private final FlightMapper flightMapper = FlightMapper.getInstance();
    @Override
    public TicketDto mapFrom(Ticket object) {
        return TicketDto.builder()
                .id(object.getId())
                .passportNo(object.getPassportNo())
                .passengerName(object.getPassengerName())
                .flight(flightMapper.mapFrom(object.getFlight()))
                .seatNo(object.getSeatNo())
                .build();
    }

    public static TicketMapper getInstance() {
        return INSTANCE;
    }
}
