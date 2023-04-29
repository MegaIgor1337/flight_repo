package by.itacademy.jdbc.starter.mapper;

import by.itacademy.jdbc.starter.dto.AircraftDto;
import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;

public class AircraftMapper implements Mapper<Aircraft, AircraftDto>{
    private static final AircraftMapper INSTANCE = new AircraftMapper();
    @Override
    public AircraftDto mapFrom(Aircraft object) {
        return AircraftDto.builder()
                .id(object.getId())
                .model(object.getModel())
                .build();
    }

    public static AircraftMapper getInstance() {
        return INSTANCE;
    }
}
