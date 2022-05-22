package com.geekbrains.spring.web.dto;

import com.geekbrains.spring.web.data.Product;


public class ProductDto {
    private Long id;

    private String title;

    private Integer cost;

    private  String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.category = product.getCategory();

    }

    public ProductDto() {
    }
}
