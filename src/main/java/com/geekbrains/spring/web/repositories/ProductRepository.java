package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByCostBetween(Integer min, Integer max);

    @Query("select p from Product p where p.cost < 80")
    List<Product> findLowRatingProducts();

    @Query("select p from Product p where p.title = :title")
    Optional<Product> findStudentByName(String title);


    boolean existsProductByTitle(String title);




    // Если бы у студентов был List<Book>, то не ленивая загрузка книг:
    // @Query("select s from Student s join fetch s.books where s.id = :id")
    // Optional<Student> findByIdWithBooks(String name);



}
