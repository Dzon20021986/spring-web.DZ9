package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.exception.AppError;
import com.geekbrains.spring.web.repositories.ProductRepository;
import com.geekbrains.spring.web.repositories.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Product> find(Integer p, Integer minCost, Integer maxCost, String partTitle) {
        Specification<Product> spec = Specification.where(null);
        // select p from Product p where true
        if (minCost != null) {
            spec = spec.and(ProductSpecification.costGreaterOrElseThan(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecification.lessThanOrEqualTo(maxCost));
        }
        if (partTitle != null) {
            spec = spec.and(ProductSpecification.likeTitle(partTitle));
        }
        // select p from Product p where true and like &title&

        return productRepository.findAll(spec, PageRequest.of(p - 1, 10));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    public ProductDto findById(Long id) {  // + Optional<Product>
        return productRepository.findById(id).map(p -> new ProductDto(p)).orElseThrow();
    }

    public List<Product> findByCost(Integer min, Integer max) {
        return productRepository.findAllByCostBetween(min, max);
    }

    public void addProduct(ProductDto productDto) {
        productRepository.save(new Product(null, productDto.getTitle(), productDto.getCost(), productDto.getCategory()));

    }

    public void changeProduct(ProductDto productDto) {
        if (productRepository.findById(productDto.getId()).isPresent()) {
            productRepository.save(new Product(productDto.getId(), productDto.getTitle(), productDto.getCost(), productDto.getCategory()));
        }

    }


    @Transactional
    public void changeCost(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setCost(product.getCost() + delta);
//        productRepository.save(product);
    }


}


//    public ResponseEntity<?> addProduct(Product product) {
//        if (productRepository.existsProductByTitle(product.getTitle())) {
//            return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(), "Product is alredy"), HttpStatus.CONFLICT);
//        }else{
//            productRepository.save(product);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//
//    }

//    public ProductDto addProduct(ProductDto product) {
//        Optional<Product> productByNew = productRepository.findById(product.getId());
//        if (productByNew.isPresent()) {
//            return productRepository.save(product);
//        }else{
//            return productRepository.save(new Product(product.getId(), product.getTitle(), product.getCost(), product.getCategory()));
//        }
//        product.getId();
//        product.getTitle();
//        product.getCost();
//        product.getCategory();
//        return productRepository.save(product.);
//    }
