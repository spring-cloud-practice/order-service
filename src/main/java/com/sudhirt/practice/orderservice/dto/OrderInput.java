package com.sudhirt.practice.orderservice.dto;

import com.sudhirt.practice.orderservice.constant.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderInput {

    List<OrderProductInput> products;
    private String customerId;
    private String addressId;
    private OrderStatus status;
}

