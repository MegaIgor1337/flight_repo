package by.itacademy.jdbc.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {
    private Long id;
    private String passportNo;
    private String passengerName;

    private FlightDto flight;
    private String seatNo;
    private BigDecimal cost;


}