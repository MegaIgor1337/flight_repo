package by.itacademy.jdbc.starter.dao.filter;

import by.itacademy.jdbc.starter.entity.flight.FlightStatus;

import java.time.LocalDateTime;

public record FlightFilter(int limit,
                           int offset,
                           String flightNo,
                           LocalDateTime departureDate,
                           String departureAirportCode,
                           LocalDateTime arrivalDate,
                           String arrivalAirportCode,
                           Long aircraftId,
                           FlightStatus status
) {
}
