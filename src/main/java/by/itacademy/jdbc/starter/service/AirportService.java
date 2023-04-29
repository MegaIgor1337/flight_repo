package by.itacademy.jdbc.starter.service;

import by.itacademy.jdbc.starter.dto.AirportDto;
import by.itacademy.jdbc.starter.dto.CrudOperations;
import by.itacademy.jdbc.starter.entity.airport.Airport;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AirportService implements CrudOperations<Airport, String> {
    private final static AirportService INSTANCE = new AirportService();


    public static AirportService getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<Airport> getEntityClass() {
        return Airport.class;
    }
}