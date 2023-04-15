package by.itacademy.jdbc.starter.exceptions;

import by.itacademy.jdbc.starter.validator.Error;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends RuntimeException {

    @Getter
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
