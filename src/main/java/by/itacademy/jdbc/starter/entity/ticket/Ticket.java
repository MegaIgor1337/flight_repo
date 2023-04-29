package by.itacademy.jdbc.starter.entity.ticket;

import by.itacademy.jdbc.starter.converter.FlightConverter;
import by.itacademy.jdbc.starter.entity.flight.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ticket", schema = "public")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "passport_no")
    private String passportNo;
    @Column(name = "passenger_name")
    private String passengerName;
    @Column(name = "flight_id")
    @Convert(converter = FlightConverter.class)
    private Flight flight;

    @Column(name = "seat_no")
    private String seatNo;
    private BigDecimal cost;


}
