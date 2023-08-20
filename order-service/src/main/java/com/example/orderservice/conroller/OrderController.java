package com.example.orderservice.conroller;


import com.example.orderservice.models.Order;
import com.example.orderservice.models.Product;
import com.example.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
@AllArgsConstructor
public class OrderController {

    OrderRepository repo;
    @GetMapping("all")
    public List<Order> getAll(){
        return repo.findAll();
    }

    @GetMapping("add")
    public String add()
    {
        repo.insert(new Order("2",152, Arrays.asList("1"), LocalDate.now()));
        return "added";
    }
    @GetMapping("get/{id}")
    public Order getById(@PathVariable("id") String id){
        return repo.findById(id).get();
    }

    @GetMapping("getProducts/{id}")
    public List<Product> getProducts(@PathVariable("id") String orderId){
        RestTemplate template = new RestTemplate();
        var producIdList = repo.findById(orderId).get().getProducts();
        List<Product> productList = new ArrayList<>();
        producIdList.forEach(p-> productList.add(template.getForObject("http://localhost:7071/product/get/"+ p, Product.class)));

        return productList;
    }


}
