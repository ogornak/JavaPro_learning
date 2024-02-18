package ru.stepup.task4.service;

import ru.stepup.task4.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    UserEntity findById(int id) throws SQLException;

    List<UserEntity> findAll() throws SQLException;

    void deleteById(int id) throws SQLException;

    UserEntity insert(UserEntity entity) throws SQLException;

    UserEntity update(UserEntity entity) throws SQLException;
}
