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

    @GetMapping()       // "/products" +
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

    @GetMapping("/{id}")  //  "/products/{id}"  поиск продукта по id +
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    // .orElseThrow(() -> new ResourceNotFoundException("Product with id : " + id + "is not found"))

    @PostMapping()  // добавление нового товара "/products"
    public void addProduct(@RequestBody ProductDto product) {
        if (product.getId() != null) {
            product.setId(null);
        }
         productService.addProduct(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto) {
         productService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")  // +
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }



    @GetMapping("/products/between")
    public List<Product> findByCost(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100") Integer max) {
        return productService.findByCost(min, max);
    }
}



// @GetMapping("products/delete/{id}")  // удаление по id +
//    public void deleteById(@PathVariable long id) {
//        productService.deleteById(id);
//    }



// @GetMapping("/products")  // вернуть все продукты +
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }


// фильтр сравнения min-max
//    @GetMapping("/select/products")
//    public ProductDto selectionProduct(@RequestParam Integer min, @RequestParam Integer max) {
//        return new ProductDto(productService);
//    }

//    @GetMapping("/products/change_cost")  // изменение стоимости
//    public void changeCost(@RequestParam Long productId, @RequestParam Integer delta) {
//        productService.changeCost(productId, delta);
//    }

//    @PostMapping()  // добавление нового товара "/products"
//    public ResponseEntity<?> addProduct(@RequestBody Product product) {
//        if (product.getId() != null) {
//            product.setId(null);
//        }
//        return productService.addProduct(product);
//    }

//    @PutMapping
//    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
//        return productService.addProduct(product);
//    }