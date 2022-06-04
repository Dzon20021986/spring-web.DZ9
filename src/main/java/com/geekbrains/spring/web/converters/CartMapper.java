package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.entities.Cart;
import com.geekbrains.spring.web.dto.CartDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.factory.Mappers;

public interface CartMapper {
    CartMapper MAPPER = Mappers.getMapper(CartMapper.class);

    Cart toCart(CartDto cartDto);

    @InheritInverseConfiguration
    CartDto fromCart(Cart cart);
}
