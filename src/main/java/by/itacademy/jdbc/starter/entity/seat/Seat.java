package by.itacademy.jdbc.starter.entity.seat;

import java.util.Objects;

public class Seat {
    private SeatKey seatKey;

    public SeatKey getSeatKey() {
        return seatKey;
    }

    @Override
    public String toString() {
        return "Seat{" +
               "seatKey=" + seatKey +
               '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(seatKey, seat.seatKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatKey);
    }

    public void setSeatKey(SeatKey seatKey) {
        this.seatKey = seatKey;
    }

    public Seat(SeatKey seatKey) {
        this.seatKey = seatKey;
    }

    public Seat() {

    }
}
