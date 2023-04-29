package by.itacademy.jdbc.starter.service;


import by.itacademy.jdbc.starter.dto.CrudOperations;
import by.itacademy.jdbc.starter.dto.UserDto;
import by.itacademy.jdbc.starter.entity.user.CreateUserDto;
import by.itacademy.jdbc.starter.entity.user.User;
import by.itacademy.jdbc.starter.exceptions.ValidationException;
import by.itacademy.jdbc.starter.mapper.CreateUserMapper;
import by.itacademy.jdbc.starter.mapper.UserMapper;
import by.itacademy.jdbc.starter.validator.CreateUserValidator;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService implements CrudOperations<User, Long> {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return get().stream().map(userMapper::mapFrom).filter(it -> it.getPersonalInfo().getEmail().equals(email) &&
                                                                    it.getPersonalInfo().getPassword().equals(password))
                .findFirst();
    }



    public Long create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        save(userEntity);
        return userEntity.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
