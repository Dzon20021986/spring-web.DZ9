package com.geekbrains.spring.web.basket;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.util.List;

@Data
@Component
@Scope("singleton")
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    private Long id;

    private String title;

    private String quantity;

    private List<Basket> basketList;












}
