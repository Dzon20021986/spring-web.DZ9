package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.entities.Cart;
import com.geekbrains.spring.web.dto.CartDto;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {
    public Cart dtoToEntity(CartDto cartDto) {

        return new Cart(null, cartDto.getProduct_id(), cartDto.getCount());
    }

    public CartDto entityToDto(Cart cart) {
        return new CartDto(cart.getId(), cart.getProduct_id(), cart.getCount());
    }


    public CartDto hardEntityToDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .count(cart.getCount())
                .build();
    }
}
