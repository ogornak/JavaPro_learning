package ru.stepup.spring.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Product {
    private Long id;
    private String account;
    private Float balance;
    private String type;
}
