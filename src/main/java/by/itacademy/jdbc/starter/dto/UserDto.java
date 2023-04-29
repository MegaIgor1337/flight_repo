package by.itacademy.jdbc.starter.dto;

import by.itacademy.jdbc.starter.entity.user.Gender;
import by.itacademy.jdbc.starter.entity.user.PersonalInfo;
import by.itacademy.jdbc.starter.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;

    private PersonalInfo personalInfo;

    private Role role;
    private Gender gender;
}
