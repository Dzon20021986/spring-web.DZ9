package com.geekbrains.spring.web.validators;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    List<String> errors = new ArrayList<>();

    public void validate(ProductDto productDto) {
        if (productDto.getCost() > 1) {
            errors.add("Стоимость не может быть меньше 1");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Название товара не может быть пустым");
        }
        if (productDto.getCategory().isEmpty()) {
            errors.add("Должна быть обязательно указана категория товара");
        }
    }
}
