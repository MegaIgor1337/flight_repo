package by.itacademy.jdbc.starter.filter;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;

public record SeatFilter(int limit,
                         int offset,
                         Long aircraftId,
                         String seatNo) {
}
