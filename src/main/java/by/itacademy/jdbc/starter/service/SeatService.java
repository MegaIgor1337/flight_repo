package by.itacademy.jdbc.starter.service;

import by.itacademy.jdbc.starter.dto.CrudOperations;
import by.itacademy.jdbc.starter.dto.SeatDto;
import by.itacademy.jdbc.starter.entity.seat.Seat;
import by.itacademy.jdbc.starter.entity.seat.SeatKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SeatService implements CrudOperations<Seat, SeatKey> {
    private final static SeatService INSTANCE = new SeatService();
    @Override
    public Class<Seat> getEntityClass() {
        return Seat.class;
    }

    public static SeatService getInstance() {
        return INSTANCE;
    }
}
