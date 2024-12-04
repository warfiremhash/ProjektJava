package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping("")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserSimpleDto> getSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toUserSimpleDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable final long id) {
        Optional<User> userOptional = userService.getUser(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userMapper.toDto(user);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmailList(email)
                .stream()
                .map(userMapper::toUserEmailDto)
                .toList();
    }

    @GetMapping("/older/{date}")
    public List<UserDto> getUserByOlder(@PathVariable LocalDate date) {
        return userService.getUsersOlderThan(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        if (userService.getUserByEmail(userDto.email()).isPresent()) {
            throw new UserAlreadyExistException.UserAlreadyExistEmailException(userDto.email());
        } else {
            User user = userMapper.toEntity(userDto);
            User createdUser = userService.createUser(user);
            return userMapper.toDto(createdUser);
        }
    }


    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        Optional<User> optionalUser = userService.getUser(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        else {
            User foundUser = optionalUser.get();
            User updatedUser = userMapper.toUpdateEntity(userDto, foundUser);
            return userService.updateUser(updatedUser);
        }
    }



}