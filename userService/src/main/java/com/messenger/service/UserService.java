package com.messenger.service;

import com.messenger.dto.UserDto;
import com.messenger.model.User;
import com.messenger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getUserById(Long id) {
        /*User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());*/
        return new UserDto(
                1L,
                "mockUser",
                "mockemail@mail.ru",
                LocalDateTime.now()
        );
    }

    public List<UserDto> getAllUsers() {
        /*return userRepository.findAll().stream()
                .map(u -> new UserDto(u.getId(), u.getUsername(), u.getEmail(), u.getCreatedAt()))
                .collect(Collectors.toList());*/
        return new ArrayList<>(List.of(new UserDto(
                1L,
                "mockUser",
                "mockemail@mail.ru",
                LocalDateTime.now())
        , new UserDto(
                2L,
                "mockUser",
                "mockemail@mail.ru",
                LocalDateTime.now()
                ),
                new UserDto(
                        1L,
                        "mockUser",
                        "mockemail@mail.ru",
                        LocalDateTime.now())));
    }
}
