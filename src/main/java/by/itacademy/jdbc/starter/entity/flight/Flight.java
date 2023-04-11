package by.itacademy.jdbc.starter.entity.flight;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.entity.airport.Airport;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private Long id;
    private String flightNo;
    private LocalDateTime departureDate;
    private Airport departureAirport;
    private LocalDateTime arrivalDate;
    private Airport arrivalAirport;
    private Aircraft aircraft;
    private FlightStatus status;

    public Flight() {

    }

    @Override
    public String toString() {
        return "Flight{" +
               "id=" + id +
               ", flightNo='" + flightNo + '\'' +
               ", departureDate=" + departureDate +
               ", departureAirportCode='" + departureAirport + '\'' +
               ", arrivalDate=" + arrivalDate +
               ", arrivalAirportCode='" + arrivalAirport + '\'' +
               ", aircraftId=" + aircraft +
               ", status=" + status +
               '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirportCode(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public Flight(
            Long id,
            String flightNo,
            LocalDateTime departureDate,
            Airport departureAirport,
            LocalDateTime arrivalDate,
            Airport arrivalAirport,
            Aircraft aircraft,
            FlightStatus status) {
        this.id = id;
        this.flightNo = flightNo;
        this.departureDate = departureDate;
        this.departureAirport = departureAirport;
        this.arrivalDate = arrivalDate;
        this.arrivalAirport = arrivalAirport;
        this.aircraft = aircraft;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && Objects.equals(flightNo, flight.flightNo) &&
               Objects.equals(departureDate, flight.departureDate) &&
               Objects.equals(departureAirport, flight.departureAirport) &&
               Objects.equals(arrivalDate, flight.arrivalDate) &&
               Objects.equals(arrivalAirport, flight.arrivalAirport) &&
               Objects.equals(aircraft, flight.aircraft) && status == flight.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNo, departureDate, departureAirport, arrivalDate,
                arrivalAirport, aircraft, status);
    }
}
