package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Page<Product> getAllProducts(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                        @RequestParam(name = "min_cost", required = false) Integer minCost,
                                        @RequestParam(name = "max_cost", required = false) Integer maxCost,
                                        @RequestParam(name = "title_part", required = false) String partTitle
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(page, minCost, maxCost, partTitle);

    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping()
    public void addProduct(@RequestBody ProductDto product) {
        if (product.getId() != null) {
            product.setId(null);
        }
         productService.addProduct(product);
    }

    @PutMapping
    public void changeProduct(@RequestBody ProductDto productDto) {
         productService.changeProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }



    @GetMapping("/between")
    public List<Product> findByCost(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100") Integer max) {
        return productService.findByCost(min, max);
    }
}
