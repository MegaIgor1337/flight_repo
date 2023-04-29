package by.itacademy.jdbc.starter.dto;

import by.itacademy.jdbc.starter.entity.seat.SeatKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDto {

    private SeatKeyDto seatKey;

}