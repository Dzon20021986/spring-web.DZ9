package com.geekbrains.spring.web.controllers;


import com.geekbrains.spring.web.entities.Cart;
import com.geekbrains.spring.web.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private  Cart cart;

    private final ProductRepository productRepository;

    public CartController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


//    @GetMapping("")
//    public List<Product> cartItems() {  // товары в корзине
//        return Cart.getProductList();
//    }


    @GetMapping("/delete{id}")
    public void delete(@PathVariable Long id) {
        productRepository.delete(id);
    }





}









//@Autowired
//public CartController(CartService cartService,ProductService productService,CartMapper mapper){
//        this.cartService=cartService;
//        this.productService=productService;
//        this.mapper=mapper;
//        }
//
//@GetMapping("")
//public List<Product> cartItems(){  // товары в корзине
//        return cartService.getProductList();
//        }
//
//@PostMapping("/add")
//public void add(@RequestBody CreateProductDto productDto,@RequestBody Integer delta){
//        cartService.addProduct(productService.find(productDto.getId()));
//        }
//
//@GetMapping("/delete{id}")
//public void deleteProduct(@RequestBody Integer productId){
//        cartService.delete(productId);
//        }

