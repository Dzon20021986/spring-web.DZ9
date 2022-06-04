package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.converters.ProductConverter;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping()
    public Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                        @RequestParam(name = "min_cost", required = false) Integer minCost,
                                        @RequestParam(name = "max_cost", required = false) Integer maxCost,
                                        @RequestParam(name = "title_part", required = false) String partTitle
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(page, minCost, maxCost, partTitle).map(productConverter::entityToDto);

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
    public void updateProduct(@RequestBody ProductDto productDto) {
         productService.update(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PatchMapping("/{id}/title")
    public void patchTitle(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productService.updateTitle(id, productDto);
    }



    @GetMapping("/between")
    public List<Product> findByCost(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100") Integer max) {
        return productService.findByCost(min, max);
    }
}
