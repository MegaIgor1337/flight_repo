package by.itacademy.jdbc.starter.converter;

import by.itacademy.jdbc.starter.entity.flight.Flight;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.AttributeConverter;
import java.util.Optional;

@NoArgsConstructor
public class FlightConverter implements AttributeConverter<Flight, Long> {

    @Override
    public Long convertToDatabaseColumn(Flight flight) {
        return flight.getId();
    }

    @Override
    public Flight convertToEntityAttribute(Long id) {

        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Flight flight = session.find(Flight.class, id);
            session.getTransaction().commit();
            return flight;
        }
    }

}

