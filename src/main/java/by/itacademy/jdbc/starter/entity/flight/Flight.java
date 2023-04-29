package by.itacademy.jdbc.starter.entity.flight;

import by.itacademy.jdbc.starter.converter.AircraftConverter;
import by.itacademy.jdbc.starter.converter.AirportConverter;
import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.entity.airport.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "flight", schema = "public")
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "flight_no")
    private String flightNo;
    @Column(name = "departure_date")
    private LocalDateTime departureDate;
    @Convert(converter = AirportConverter.class)
    @Column(name = "departure_airport_code")
    private Airport departureAirport;
    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;
    @Convert(converter = AirportConverter.class)
    @Column(name = "arrival_airport_code")
    private Airport arrivalAirport;
    @Convert(converter = AircraftConverter.class)
    @Column(name = "aircraft_id")
    private Aircraft aircraft;
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

}
