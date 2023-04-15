package by.itacademy.jdbc.starter.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);
}
