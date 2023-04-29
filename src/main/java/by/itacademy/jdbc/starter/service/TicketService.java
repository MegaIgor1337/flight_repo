package by.itacademy.jdbc.starter.service;


import by.itacademy.jdbc.starter.dto.CrudOperations;
import by.itacademy.jdbc.starter.dto.TicketDto;
import by.itacademy.jdbc.starter.entity.ticket.Ticket;
import by.itacademy.jdbc.starter.mapper.TicketMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketService implements CrudOperations<Ticket,  Long> {
    private final static TicketService INSTANCE = new TicketService();
    private final TicketMapper ticketMapper = new TicketMapper();


    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return get().stream().map(ticketMapper::mapFrom).filter(it -> Objects.equals(it.getFlight().getId(), flightId))
                .toList();
    }

    @Override
    public Class<Ticket> getEntityClass() {
        return Ticket.class;
    }
}
