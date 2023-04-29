package by.itacademy.jdbc.starter.entity.seat;

import by.itacademy.jdbc.starter.converter.AircraftConverter;
import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class SeatKey implements Serializable {
    @Convert(converter = AircraftConverter.class)
    @Column(name = "aircraft_id")
    private Aircraft aircraft;
    @Column(name = "seat_no")
    private String SeatNo;


}
