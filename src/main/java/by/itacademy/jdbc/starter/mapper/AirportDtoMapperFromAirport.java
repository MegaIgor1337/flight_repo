package by.itacademy.jdbc.starter.mapper;

import by.itacademy.jdbc.starter.dto.AirportDto;
import by.itacademy.jdbc.starter.entity.airport.Airport;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AirportDtoMapperFromAirport implements Mapper<AirportDto, Airport> {
    private static final AirportDtoMapperFromAirport INSTANCE = new AirportDtoMapperFromAirport();
    @Override
    public Airport mapFrom(AirportDto object) {
        return Airport.builder()
                .code(object.getCode())
                .country(object.getCountry())
                .city(object.getCity())
                .build();
    }
    public static AirportDtoMapperFromAirport getInstance() {
        return INSTANCE;
    }
}
