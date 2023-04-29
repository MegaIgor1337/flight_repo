package by.itacademy.jdbc.starter.converter;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.util.Optional;
import by.itacademy.jdbc.starter.entity.user.BirthDay;


public class BirthDayConverter implements AttributeConverter<BirthDay, Date> {
    @Override
    public Date convertToDatabaseColumn(BirthDay birthDay) {
        return Optional.ofNullable(birthDay)
                .map(BirthDay::birthDay)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public BirthDay convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toLocalDate)
                .map(BirthDay::new)
                .orElse(null);
    }
}
