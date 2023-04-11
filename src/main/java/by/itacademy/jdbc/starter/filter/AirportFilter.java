package by.itacademy.jdbc.starter.filter;

public record AirportFilter(int limit,
                            int offset,
                            String code,
                            String country,
                            String city) {
}
