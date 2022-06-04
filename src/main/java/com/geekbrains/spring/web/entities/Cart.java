package com.geekbrains.spring.web.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//   @Column(name = "status")
//   private String status= "not empty";

//    private Map<Product, Integer> orderList;

//    @Column(name = "product_id")
    private Long product_id;

//    @Column(name = "count")
    private Long count;

















//    @PostConstruct
//    public void init() {
//        this.orderList = new Map<>() {
//        };
//    }


//    public void addProductByCart() {
//        orderList = new ArrayList<>(Arrays.asList(
//
//        ));
//    }


//    public void add(Product product) {
//        orderList = new ArrayList<>(Arrays.asList(
//
//        ));
//    }

//    public static List<Product> getProductList() {
//        return null;
//    }


//    public Product findById(Long id) {
//        return orderList.stream().filter(p -> p.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//    }

//    public void add(Product product) {
//        if (product.equals(findById(1L))) {
//            orderList.add(product);
//        } else {
//            System.out.println("Product not found" + id);
//        }
//    }


}
