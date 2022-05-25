package com.geekbrains.spring.web.order;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Order {

    private List<Order> orderList;

    public void init() {
        orderList = new ArrayList<>(Arrays.asList(
                new Order()
        ));
    }


}
