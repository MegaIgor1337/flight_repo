package by.itacademy.jdbc.starter.mapper;

import by.itacademy.jdbc.starter.dto.FlightDto;
import by.itacademy.jdbc.starter.entity.flight.Flight;

public class FlightMapper implements Mapper<Flight, FlightDto> {
    private final static FlightMapper INSTANCE = new FlightMapper();
    private final AircraftMapper aircraftMapper = AircraftMapper.getInstance();
    private final AirportMapper airportMapper = AirportMapper.getInstance();
    @Override
    public FlightDto mapFrom(Flight object) {
        return FlightDto.builder()
                .id(object.getId())
                .flightNo(object.getFlightNo())
                .departureDate(object.getDepartureDate())
                .departureAirport(airportMapper.mapFrom(object.getDepartureAirport()))
                .arrivalDate(object.getArrivalDate())
                .arrivalAirport(airportMapper.mapFrom(object.getArrivalAirport()))
                .aircraft(aircraftMapper.mapFrom(object.getAircraft()))
                .status(object.getStatus()).build();
    }

    public static FlightMapper getInstance() {
        return INSTANCE;
    }
}
