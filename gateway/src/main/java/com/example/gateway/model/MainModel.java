package com.example.gateway.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MainModel {

    String name;
    String username;
    public Map<String,Map<String,Integer>> orderDetails = new HashMap<>();
    public Map<String,Double> orderSums = new HashMap<>();

    public MainModel() {

    }
}
