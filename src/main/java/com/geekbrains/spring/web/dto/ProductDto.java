package com.geekbrains.spring.web.dto;

import com.geekbrains.spring.web.data.Product;
import lombok.Data;


@Data
public class ProductDto {
    private Long id;

    private String title;

    private Integer cost;

    private  String category;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.category = product.getCategory();

    }

    public ProductDto() {
    }
}
