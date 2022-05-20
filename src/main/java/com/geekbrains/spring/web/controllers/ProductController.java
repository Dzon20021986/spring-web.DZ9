package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.exception.AppError;
import com.geekbrains.spring.web.exception.ResourceNotFoundException;
import com.geekbrains.spring.web.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping("/products")  // вернуть все продукты +
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @GetMapping("/products")
    public Page<Product> getAllProducts(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                        @RequestParam(name = "min_cost", required = false) Integer minCost,
                                        @RequestParam(name = "maxcost", required = false) Integer maxCost,
                                        @RequestParam(name = "title_part", required = false) String partTitle
        ) {
        if (page < 1){
            page = 1;
        }
        return productService.find(page, maxCost, minCost, partTitle);

    }

    @GetMapping("/products/{id}")  // поиск продукта по id +
    public Product findById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id : " + id + "is not found"));
    }

    @GetMapping("products/delete/{id}")  // удаление по id +
    public void deleteById(@PathVariable long id) {
        productService.deleteById(id);
    }

    @PostMapping("/products")  // добавление нового товара
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
       return productService.addProduct(product);
    }

    @GetMapping("/products/change_cost")  // изменение стоимости
    public void changeCost(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeCost(productId, delta);
    }

    // фильтр сравнения min-max
//    @GetMapping("/select/products")
//    public ProductDto selectionProduct(@RequestParam Integer min, @RequestParam Integer max) {
//        return new ProductDto(productService);
//    }


    @GetMapping("/products/between")
    public List<Product> findByCost(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100") Integer max) {
        return productService.findByCost(min, max);
    }
}
