package by.itacademy.jdbc.starter.mapper;

import by.itacademy.jdbc.starter.entity.user.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .personalInfo(
                        PersonalInfo.builder()
                                .birthDate(new BirthDay(LocalDate.parse(object.getBirthday())))
                                .email(object.getEmail())
                                .password(object.getPassword())
                                .build()
                )
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}