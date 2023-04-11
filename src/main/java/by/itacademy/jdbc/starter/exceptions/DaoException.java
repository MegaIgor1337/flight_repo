package by.itacademy.jdbc.starter.exceptions;

import java.sql.SQLException;

public class DaoException extends RuntimeException {
    public DaoException(Throwable e) {
        super(e);
    }
}
