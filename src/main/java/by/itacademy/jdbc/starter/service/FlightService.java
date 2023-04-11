package by.itacademy.jdbc.starter.service;

import by.itacademy.jdbc.starter.dao.FlightDao;
import by.itacademy.jdbc.starter.dto.FlightDto;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private final static FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getInstance();


    public List<FlightDto> findAll() {
        return flightDao.findAll().stream().map(
                flight -> new FlightDto(
                        flight.getId(),
                        """
                                   %s - %s - %s
                                """.formatted(
                                flight.getDepartureAirport(),
                                flight.getArrivalAirport(),
                                flight.getStatus()
                        )
                )
        ).collect(Collectors.toList());
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }

    private FlightService() {
    }
}
