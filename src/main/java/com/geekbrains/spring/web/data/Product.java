package com.geekbrains.spring.web.data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;

    private Integer cost;

    private  String category;

    public Product() {
    }

    public Product(Long id, String title, Integer cost, String category) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.category = category;
    }
}
