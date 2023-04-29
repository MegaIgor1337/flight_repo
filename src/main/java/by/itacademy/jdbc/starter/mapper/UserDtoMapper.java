package by.itacademy.jdbc.starter.mapper;

import by.itacademy.jdbc.starter.dto.UserDto;
import by.itacademy.jdbc.starter.entity.user.*;

import java.time.LocalDate;


public class UserDtoMapper implements Mapper<CreateUserDto, UserDto> {
    private static final UserDtoMapper INSTANCE = new UserDtoMapper();
    @Override
    public UserDto mapFrom(CreateUserDto object) {
        return UserDto.builder()
                .id(Long.valueOf(object.getId()))
                .name(object.getName())
                .personalInfo(PersonalInfo.builder()
                        .birthDate(new BirthDay(LocalDate.parse(object.getBirthday())))
                        .email(object.getEmail())
                        .password(object.getPassword())
                        .build())
                .role(Role.valueOf(object.getRole()))
                .gender(Gender.valueOf(object.getGender()))
                .build();
    }

    public static UserDtoMapper getInstance() {
        return INSTANCE;
    }
}
