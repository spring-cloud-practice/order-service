package com.sudhirt.practice.orderservice.dto;

import lombok.Data;

@Data
public class OrderProductInput {

    private String productId;
    private Long quantity;
}
