package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.exception.AppError;
import com.geekbrains.spring.web.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findById(Long id) {
       return productRepository.findById(id);
    }

    public List<Product> findByCost(Integer min, Integer max) {
        return productRepository.findAllByCostBetween(min, max);
    }

    public ResponseEntity<?> addProduct(Product product) {
        if (productRepository.existsProductByTitle(product.getTitle())) {
            return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(), "Product is alredy"), HttpStatus.CONFLICT);
        }else{
            productRepository.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @Transactional
    public void changeCost(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setCost(product.getCost() + delta);
//        studentRepository.save(student);
    }


//    public Page<Product> findAllByBetween(Optional<BigDecimal> min, Optional<BigDecimal> max, Pageable pageable) {
//        if (min.isPresent() && max.isPresent()) {
//            return productRepository.findAllByCostBetween(min.get(), max.get(), pageable);
//        }
//        if (min.isPresent()) {
//            return productRepository.findAllByCostMinEqual(min.get(), pageable);
//        }
//        if (max.isPresent()) {
//            return productRepository.findAllByCostMaxEqual(max.get(), pageable);
//        }
//        return productRepository.findAll(pageable);
//    }

}
