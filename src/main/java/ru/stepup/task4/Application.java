package ru.stepup.task4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.stepup.task4.entity.UserEntity;
import ru.stepup.task4.service.UserService;

import java.sql.SQLException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        var context = SpringApplication.run(Application.class, args);
        var userService = context.getBean(UserService.class);
        try {
            System.out.println(userService.findAll());
            var user = userService.insert(new UserEntity("Иван"));
            System.out.println(user);
            user = userService.findById(user.getId());
            System.out.println(user);
            user.setUsername("Валерий");
            user = userService.update(user);
            System.out.println(user);
            userService.deleteById(user.getId());
            System.out.println(userService.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
