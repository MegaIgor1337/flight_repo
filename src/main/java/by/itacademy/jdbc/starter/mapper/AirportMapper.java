package by.itacademy.jdbc.starter.mapper;

import by.itacademy.jdbc.starter.dto.AirportDto;
import by.itacademy.jdbc.starter.entity.airport.Airport;

public class AirportMapper implements Mapper<Airport, AirportDto> {
    private static final AirportMapper INSTANCE = new AirportMapper();
    @Override
    public AirportDto mapFrom(Airport object) {
        return AirportDto.builder()
                .code(object.getCode())
                .country(object.getCountry())
                .city(object.getCity())
                .build();
    }

    public static AirportMapper getInstance() {
        return INSTANCE;
    }
}
