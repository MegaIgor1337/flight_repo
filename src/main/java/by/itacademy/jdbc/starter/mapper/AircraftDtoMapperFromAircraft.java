package by.itacademy.jdbc.starter.mapper;

import by.itacademy.jdbc.starter.dto.AircraftDto;
import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AircraftDtoMapperFromAircraft implements Mapper<AircraftDto, Aircraft> {
    private static final AircraftDtoMapperFromAircraft INSTANCE = new AircraftDtoMapperFromAircraft();
    @Override
    public Aircraft mapFrom(AircraftDto object) {
        return Aircraft.builder()
                .id(object.getId())
                .model(object.getModel())
                .build();
    }

    public static AircraftDtoMapperFromAircraft getInstance() {
        return INSTANCE;
    }
}
