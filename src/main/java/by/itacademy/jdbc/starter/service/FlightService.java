package by.itacademy.jdbc.starter.service;

import by.itacademy.jdbc.starter.dto.CrudOperations;
import by.itacademy.jdbc.starter.dto.FlightDto;
import by.itacademy.jdbc.starter.entity.flight.Flight;
import by.itacademy.jdbc.starter.mapper.FlightDtoMapperFromFlight;
import by.itacademy.jdbc.starter.mapper.FlightMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightService implements CrudOperations<Flight, Long> {
    private static final FlightService INSTANCE = new FlightService();
    private final FlightMapper flightMapper = FlightMapper.getInstance();

    public List<FlightDto> getDto() {
        return get().stream()
                .map(flightMapper::mapFrom).toList();
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<Flight> getEntityClass() {
        return Flight.class;
    }
}