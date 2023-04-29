package by.itacademy.jdbc.starter.entity.user;


import by.itacademy.jdbc.starter.converter.BirthDayConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonalInfo {
    private String email;
    private String password;
    @Convert(converter =  BirthDayConverter.class)
    @Column(name = "birthday")
    private BirthDay birthDate;
}
