package by.itacademy.jdbc.starter.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}