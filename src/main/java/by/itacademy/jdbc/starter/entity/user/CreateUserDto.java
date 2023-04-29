package by.itacademy.jdbc.starter.entity.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDto {
    String id;
    String name;
    String birthday;
    String email;
    String password;
    String role;
    String gender;
}
