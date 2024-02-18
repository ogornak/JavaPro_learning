package ru.stepup.task4.dta;

import ru.stepup.task4.config.DataSource;
import ru.stepup.task4.entity.UserEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDta {
    private static final String QUERY_SELECT_ALL = "select * from users;";
    private static final String QUERY_SELECT_BY_ID = "select * from users where id = ?;";
    private static final String QUERY_DELETE_BY_ID = "delete from users where id = ?;";
    private static final String QUERY_INSERT = "insert into users (username) values (?) returning id, username;";
    private static final String QUERY_UPDATE = "update users set username = ? where id = ? returning id, username;";
    private final DataSource dataSource;

    public UserDta(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UserEntity findById(int id) throws SQLException {
        UserEntity entity = null;
        try (var connection = dataSource.getConnection();
             var prepareStatement = connection.prepareStatement(QUERY_SELECT_BY_ID)) {
            prepareStatement.setInt(1, id);
            try (var rs = prepareStatement.executeQuery()) {
                while (rs.next()) {
                    entity = new UserEntity(rs.getInt("id"), rs.getString("username"));
                }
            }
        }
        return entity;
    }

    public List<UserEntity> findAll() throws SQLException {
        List<UserEntity> entities = new ArrayList<>();
        try (var connection = dataSource.getConnection();
             var prepareStatement = connection.prepareStatement(QUERY_SELECT_ALL);
             var rs = prepareStatement.executeQuery()) {
            while (rs.next()) {
                entities.add(new UserEntity(rs.getInt("id"), rs.getString("username")));
            }
        }
        return entities;
    }

    public void deleteById(int id) throws SQLException {
        try (var connection = dataSource.getConnection();
             var prepareStatement = connection.prepareStatement(QUERY_DELETE_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.execute();
        }
    }

    public UserEntity insert(UserEntity entity) throws SQLException {
        try (var connection = dataSource.getConnection();
             var prepareStatement = connection.prepareStatement(QUERY_INSERT)) {
            prepareStatement.setString(1, entity.getUsername());
            try (var rs = prepareStatement.executeQuery()) {
                while (rs.next()) {
                    entity = new UserEntity(rs.getInt("id"), rs.getString("username"));
                }
            }
        }
        return entity;
    }

    public UserEntity update(UserEntity entity) throws SQLException {
        try (var connection = dataSource.getConnection();
             var prepareStatement = connection.prepareStatement(QUERY_UPDATE)) {
            prepareStatement.setString(1, entity.getUsername());
            prepareStatement.setInt(2, entity.getId());
            try (var rs = prepareStatement.executeQuery()) {
                while (rs.next()) {
                    entity = new UserEntity(rs.getInt("id"), rs.getString("username"));
                }
            }
        }
        return entity;
    }
}
