package com.sudhirt.practice.orderservice.dto;

import lombok.Data;

@Data
public class Product {

    private String id;
    private String name;
    private String description;
    private Long stockQuantity;
    private Double price;
}
