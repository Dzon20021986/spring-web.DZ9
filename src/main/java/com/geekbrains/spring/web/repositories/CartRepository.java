package com.geekbrains.spring.web.repositories;


import com.geekbrains.spring.web.entities.Cart;
import com.geekbrains.spring.web.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {

    @Query("select c from Cart c where c.id = :cart")  // ??? запрос для получения всех продуктов по id корзины.
    List<Product> findAllByCart(Long id);
}
