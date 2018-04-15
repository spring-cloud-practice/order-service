package com.sudhirt.practice.orderservice.dto;

import com.sudhirt.practice.orderservice.constant.OrderStatus;
import lombok.Data;

@Data
public class OrderSearchCriteria {

    private String productId;
    private OrderStatus orderStatus;

}
