package by.itacademy.jdbc.starter.filter;

public record AircraftFilter(int limit,
                             int offset,
                             String model) {
}
