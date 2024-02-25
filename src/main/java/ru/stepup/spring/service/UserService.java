package ru.stepup.spring.service;

import ru.stepup.spring.dto.User;

public interface UserService {
    User findById(long userId);
}
