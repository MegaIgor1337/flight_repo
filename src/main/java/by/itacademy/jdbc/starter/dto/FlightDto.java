package by.itacademy.jdbc.starter.dto;

import by.itacademy.jdbc.starter.entity.flight.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDto {
    private Long id;
    private String flightNo;

    private LocalDateTime departureDate;
    private AirportDto departureAirport;
    private LocalDateTime arrivalDate;
    private AirportDto arrivalAirport;

    private AircraftDto aircraft;
    private FlightStatus status;

}
