package by.itacademy.jdbc.starter.dao.filter;

public record AircraftFilter(int limit,
                             int offset,
                             String model) {
}
