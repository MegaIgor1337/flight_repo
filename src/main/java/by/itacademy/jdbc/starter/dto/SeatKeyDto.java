package by.itacademy.jdbc.starter.dto;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatKeyDto {
    private AircraftDto aircraft;
    private String SeatNo;
}
