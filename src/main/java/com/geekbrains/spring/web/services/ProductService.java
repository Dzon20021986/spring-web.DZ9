package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.converters.ProductConverter;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.exception.ValidateException;
import com.geekbrains.spring.web.repositories.ProductRepository;
import com.geekbrains.spring.web.repositories.specification.ProductSpecification;
import com.geekbrains.spring.web.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;


    public Page<Product> find(Integer p, Integer maxCost, Integer minCost, String partTitle) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductSpecification.costGreaterOrElseThan(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecification.lessThanOrEqualTo(maxCost));
        }
        if (partTitle != null) {
            spec = spec.and(ProductSpecification.likeTitle(partTitle));
        }
        return productRepository.findAll(spec, PageRequest.of(p - 1, 10));
    }

    public void deleteById(Long id) {
        log.info("Product {} is delete", id);
        productRepository.deleteById(id);
    }


    public ProductDto findById(Long id) {
        return productRepository.findById(id).map(productConverter::entityToDto).orElseThrow();

        // new ProductDto(p)).orElseThrow();
    }

    public List<Product> findByCost(Integer min, Integer max) {
        return productRepository.findAllByCostBetween(min, max);
    }

    public  ProductDto addProduct(ProductDto product) {
        productValidator.validate(product);
        productRepository.save(productConverter.dtoToEntity(product));
        return product;
    }


    @Transactional
    public ProductDto update(ProductDto productDto) {
        if (productRepository.existsProductById(productDto.getId())) {
            throw new ValidateException(List.of("Продукта с таким id не существует"));
        }
        Product product = productRepository.getById(productDto.getId());
        product.setCost(productDto.getCost());
        product.setTitle(productDto.getTitle());
        product.setCategory(productDto.getCategory());
        return productDto;

    }


    @Transactional
    public void changeCost(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setCost(product.getCost() + delta);
//        productRepository.save(product);
    }


    @Transactional
    public void updateTitle(Long id, ProductDto productDto) {
        Product product = productRepository.getById(productDto.getId());
        product.setTitle(productDto.getTitle());
    }
}

//    public void changeProduct(ProductDto productDto) {
//        if (productRepository.findById(productDto.getId()).isPresent()) {
//            productRepository.save(new Product(productDto.getId(), productDto.getTitle(), productDto.getCost(), productDto.getCategory()));
//        }

//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }


//    public void addProduct(ProductDto productDto) {
//        productRepository.save(new Product(null, productDto.getTitle(), productDto.getCost(), productDto.getCategory()));
//    }


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
