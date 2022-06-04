package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto) {
        return new Product(null, productDto.getTitle(), productDto.getCost(), productDto.getCategory());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost(), product.getCategory());
    }

    public ProductDto hardEntityToDto(Product product) {
        return ProductDto.builder()
                .cost(product.getCost())
                .title(product.getTitle())
                .category(product.getCategory())
                .build();
    }
}
