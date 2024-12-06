package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserEmailDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    public User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    UserSimpleDto toUserSimpleDto(User user) {
        return new UserSimpleDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    UserEmailDto toUserEmailDto(User user) {
        return new UserEmailDto(
                user.getId(),
                user.getEmail()
        );
    }

    User toUpdateEntity(UserDto userDto, User user) {
        if(userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }
        if(userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }
        if(userDto.birthdate() != null) {
            user.setBirthdate(userDto.birthdate());
        }
        if(userDto.email() != null) {
            user.setEmail(userDto.email());
        }
        return user;
    }

}
