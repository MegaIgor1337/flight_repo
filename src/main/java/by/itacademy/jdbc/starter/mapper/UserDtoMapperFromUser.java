package by.itacademy.jdbc.starter.mapper;

import by.itacademy.jdbc.starter.dto.UserDto;
import by.itacademy.jdbc.starter.entity.user.BirthDay;
import by.itacademy.jdbc.starter.entity.user.PersonalInfo;
import by.itacademy.jdbc.starter.entity.user.User;

import java.time.LocalDate;

public class UserDtoMapperFromUser implements Mapper<User, UserDto>{
    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .personalInfo(PersonalInfo.builder()
                        .birthDate(object.getPersonalInfo().getBirthDate())
                        .email(object.getPersonalInfo().getEmail())
                        .password(object.getPersonalInfo().getPassword())
                        .build())
                .role(object.getRole())
                .gender(object.getGender())
                .build();
    }
}
