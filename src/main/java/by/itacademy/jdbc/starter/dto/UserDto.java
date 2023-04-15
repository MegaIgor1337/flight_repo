package by.itacademy.jdbc.starter.dto;

import by.itacademy.jdbc.starter.entity.user.Gender;
import by.itacademy.jdbc.starter.entity.user.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    Role role;
    Gender gender;
}
