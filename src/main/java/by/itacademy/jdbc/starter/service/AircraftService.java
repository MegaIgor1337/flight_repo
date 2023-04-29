package by.itacademy.jdbc.starter.service;

import by.itacademy.jdbc.starter.dto.AircraftDto;
import by.itacademy.jdbc.starter.dto.CrudOperations;
import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AircraftService implements CrudOperations<Aircraft, Long> {
    private final static AircraftService INSTANCE = new AircraftService();


    public static AircraftService getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<Aircraft> getEntityClass() {
        return Aircraft.class;
    }
}