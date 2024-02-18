package ru.stepup.task4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.stepup.task4.dta.UserDta;

@Configuration
public class Config {
    @Bean
    public UserDta getUserDta(DataSource dateSource) {
        return new UserDta(dateSource);
    }
}
