package com.example.gateway;


import com.example.gateway.model.MainModel;
import com.example.gateway.model.Order;
import com.example.gateway.model.Product;
import com.example.gateway.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class GatewayController {

    @Autowired
    RestTemplate template;

    @GetMapping("def")
    public String defs(){
        return "works";
    }

    @GetMapping("{id}")
    public MainModel getSum(@PathVariable("id") String id){
       User user =  template.getForObject("http://user-service/user/get/"+id,User.class);
       List<Order> orderList = new ArrayList<>();
       user.getOrderIds().forEach(orderId -> orderList.add(template.getForObject("http://order-service/order/get/"+orderId,Order.class)));
        System.out.println("AAAAA"+orderList.get(0));








       MainModel main = new MainModel();
       main.setName(user.getName());
       main.setUsername(user.getUsername());
for(var order:orderList){

     Map<String,Integer> newMap = new HashMap<>();
     for(var product:order.getProducts()){
         Product newProduct = template.getForObject("http://product-service/product/get/"+product,Product.class);
         newMap.put(newProduct.getName(),newProduct.getPrice());
      }

     main.orderDetails.put(order.getId(),newMap);

     main.orderSums.put(order.getId(),(double)order.getAmount());
 }


       return main;
    }



}
