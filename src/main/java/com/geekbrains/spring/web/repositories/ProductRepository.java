package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByCostBetween(Integer min, Integer max);

    @Query("select p from Product p where p.cost < 80")
    List<Product> findLowRatingProducts();

    @Query("select p from Product p where p.title = :title")
    Optional<Product> findStudentByName(String title);


    boolean existsProductByTitle(String title);

//    Page<ProductDto> findAll(Specification<Product> spec, PageRequest of);



    // Если бы у студентов был List<Book>, то не ленивая загрузка книг:
    // @Query("select s from Student s join fetch s.books where s.id = :id")
    // Optional<Student> findByIdWithBooks(String name);



}
