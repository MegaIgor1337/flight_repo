package by.itacademy.jdbc.starter.converter;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.service.AircraftService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.AttributeConverter;
import java.util.Optional;

public class AircraftConverter implements AttributeConverter<Aircraft, Long> {
    private final AircraftService aircraftService = AircraftService.getInstance();
    @Override
    public Long convertToDatabaseColumn(Aircraft aircraft) {
        return aircraft.getId();
    }

    @Override
    public Aircraft convertToEntityAttribute(Long id) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Aircraft aircraft = session.find(Aircraft.class, id);
            session.getTransaction().commit();
            return aircraft;
        }
    }
}
