package ru.stepup.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.spring.dto.User;
import ru.stepup.spring.repository.UserRepository;
import ru.stepup.spring.service.UserService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User findById(long userId) {
        var entity = repository.findById(userId);
        if (entity.isEmpty()) {
            throw new NoSuchElementException("User " + userId + " was not found");
        }
        return new User().setId(userId).setAccount(entity.get().getAccount());
    }
}
