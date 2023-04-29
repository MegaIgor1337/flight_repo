package by.itacademy.jdbc.starter.converter;

import by.itacademy.jdbc.starter.entity.airport.Airport;
import by.itacademy.jdbc.starter.service.AirportService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.AttributeConverter;
import java.util.Optional;

public class AirportConverter implements AttributeConverter<Airport, String> {
    private final AirportService airportService = AirportService.getInstance();
    @Override
    public String convertToDatabaseColumn(Airport airport) {
        return airport.getCode();
    }

    @Override
    public Airport convertToEntityAttribute(String s) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Airport airport = session.find(Airport.class, s);
            session.getTransaction().commit();
            return airport;
        }
    }
}
