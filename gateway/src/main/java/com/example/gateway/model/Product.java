package com.example.gateway.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Getter
public class Product {
    @Id
    String id;
    String name;
    int price;

    public Product() {
    }
}

