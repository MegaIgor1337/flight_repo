package by.itacademy.jdbc.starter.mapper;
import by.itacademy.jdbc.starter.dto.UserDto;
import by.itacademy.jdbc.starter.entity.user.PersonalInfo;
import by.itacademy.jdbc.starter.entity.user.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .personalInfo(PersonalInfo.builder()
                        .password(object.getPersonalInfo().getPassword())
                        .email(object.getPersonalInfo().getEmail())
                        .birthDate(object.getPersonalInfo().getBirthDate()).build())
                .role(object.getRole())
                .gender(object.getGender())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}