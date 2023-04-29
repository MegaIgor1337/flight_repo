package by.itacademy.jdbc.starter.mapper;

import by.itacademy.jdbc.starter.dto.FlightDto;
import by.itacademy.jdbc.starter.entity.flight.Flight;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FlightDtoMapperFromFlight implements Mapper<FlightDto, Flight>{
    private static final FlightDtoMapperFromFlight INSTANCE = new FlightDtoMapperFromFlight();

    private final AirportDtoMapperFromAirport airportDtoMapperFromAirport = AirportDtoMapperFromAirport.getInstance();
    private final AircraftDtoMapperFromAircraft aircraftDtoMapperFromAircraft
            = AircraftDtoMapperFromAircraft.getInstance();
    @Override
    public Flight mapFrom(FlightDto object) {
        return Flight.builder()
                .id(object.getId())
                .flightNo(object.getFlightNo())
                .departureDate(object.getDepartureDate())
                .departureAirport(airportDtoMapperFromAirport.mapFrom(object.getDepartureAirport()))
                .arrivalDate(object.getArrivalDate())
                .arrivalAirport(airportDtoMapperFromAirport.mapFrom(object.getArrivalAirport()))
                .aircraft(aircraftDtoMapperFromAircraft.mapFrom(object.getAircraft()))
                .status(object.getStatus())
                .build();
    }

    public static FlightDtoMapperFromFlight getInstance() {
        return INSTANCE;
    }
}
