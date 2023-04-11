package by.itacademy.jdbc.starter.entity.seat;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;

import java.util.Objects;

public class SeatKey {
    private Aircraft aircraft;
    private String SeatNo;

    private Aircraft newAircraft;
    private String newSeatNo;

    public Aircraft getNewAircraft() {
        return newAircraft;
    }

    public SeatKey(Aircraft aircraft,
                   String seatNo, Aircraft newValue, String newSeatNo) {
        this.aircraft = aircraft;
        SeatNo = seatNo;
        this.newAircraft = newValue;
        this.newSeatNo = newSeatNo;
    }

    public void setNewAircraft(Aircraft newValue) {
        this.newAircraft = newValue;
    }

    public String getNewSeatNo() {
        return newSeatNo;
    }

    public void setNewSeatNo(String newSeatNo) {
        this.newSeatNo = newSeatNo;
    }

    public SeatKey() {

    }

    public SeatKey(Aircraft aircraft, String seatNo) {
        this.aircraft = aircraft;
        SeatNo = seatNo;
    }

    @Override
    public String toString() {
        return "SeatKey{" +
               "aircraftID=" + aircraft +
               ", SeatNo='" + SeatNo + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatKey seatKey = (SeatKey) o;
        return Objects.equals(aircraft, seatKey.aircraft) && Objects.equals(SeatNo, seatKey.SeatNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraft, SeatNo);
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(String seatNo) {
        SeatNo = seatNo;
    }
}
