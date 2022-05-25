package com.geekbrains.spring.web.dto;

import com.geekbrains.spring.web.data.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;

    private String title;

    private Integer cost;

    private  String category;
}
