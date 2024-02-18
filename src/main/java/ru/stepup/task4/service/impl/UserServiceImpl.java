package ru.stepup.task4.service.impl;

import org.springframework.stereotype.Service;
import ru.stepup.task4.dta.UserDta;
import ru.stepup.task4.entity.UserEntity;
import ru.stepup.task4.service.UserService;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDta userDta;

    public UserServiceImpl(UserDta userDta) {
        this.userDta = userDta;
    }

    @Override
    public UserEntity findById(int id) throws SQLException {
        return userDta.findById(id);
    }

    @Override
    public List<UserEntity> findAll() throws SQLException {
        return userDta.findAll();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        userDta.deleteById(id);
    }

    @Override
    public UserEntity insert(UserEntity entity) throws SQLException {
        return userDta.insert(entity);
    }

    @Override
    public UserEntity update(UserEntity entity) throws SQLException {
        return userDta.update(entity);
    }
}
