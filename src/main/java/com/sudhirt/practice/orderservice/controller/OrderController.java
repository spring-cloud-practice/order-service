package com.sudhirt.practice.orderservice.controller;

import com.sudhirt.practice.orderservice.client.CustomerServiceClient;
import com.sudhirt.practice.orderservice.constant.FulfillmentStatus;
import com.sudhirt.practice.orderservice.constant.OrderStatus;
import com.sudhirt.practice.orderservice.domain.Order;
import com.sudhirt.practice.orderservice.dto.*;
import com.sudhirt.practice.orderservice.client.CatalogServiceClient;
import com.sudhirt.practice.orderservice.domain.Item;
import com.sudhirt.practice.orderservice.dto.*;
import com.sudhirt.practice.orderservice.exception.NotFoundException;
import com.sudhirt.practice.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private CatalogServiceClient catalogServiceClient;

    @GetMapping("/orders")
    public List<Order> getOrders(@RequestBody(required = false) OrderSearchCriteria criteria) {
        if (criteria != null) {
            return orderRepository.findAll(
                    Example.of(
                            Order.builder().items(
                                    Arrays.asList(Item.builder()
                                            .id(criteria.getProductId())
                                            .build()))
                                    .orderStatus(criteria.getOrderStatus())
                                    .build()));
        } else {
            return orderRepository.findAll();
        }
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable(name = "id") String id) {
        Optional<Order> orderHolder = orderRepository.findById(id);
        return orderHolder.map(order -> {
            return order;
        }).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderInput orderInput) {
        if(!isValidAddress(orderInput)) {
            throw new NotFoundException("Invalid address specified");
        }
        Map<String, Product> catalogProducts = getProductsFromCatalog(orderInput);
        Order order = Order.builder()
                .customerId(orderInput.getCustomerId())
                .addressId(orderInput.getAddressId())
                .orderStatus(OrderStatus.PENDING_PAYMENT)
                .orderDate(new Date())
                .orderPrice(calculatePrice(catalogProducts, orderInput))
                .fulfillmentStatus(FulfillmentStatus.PENDING)
                .build();
        orderInput.getProducts().stream().forEach(product -> {
            order.add(Item.builder()
                    .productId(product.getProductId())
                    .fulfillmentStatus(FulfillmentStatus.PENDING)
                    .quantity(product.getQuantity())
                    .perUnitCost(catalogProducts.get(product.getProductId()).getPrice())
                    .build());
        });
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    @PutMapping("/orders/{id}")
    public String updateOrder(@PathVariable(name = "id") String id, @RequestBody OrderInput orderInput) {
        Optional<Order> orderHolder = orderRepository.findById(id);
        return orderHolder.map(order -> {
            order.setOrderStatus(orderInput.getStatus());
            orderRepository.save(order);
            return order.getId();
        }).orElseThrow(NotFoundException::new);
    }

    private Double calculatePrice(Map<String, Product> catalogProducts, OrderInput orderInput) {
        //return orderInput.getProducts().stream().map(OrderProductInput::getCost).reduce(0d, Double::sum);
        Double price = 0d;
        Product catalogProduct;
        for (OrderProductInput orderProductInput : orderInput.getProducts()) {
            catalogProduct = catalogProducts.get(orderProductInput.getProductId());
            price += catalogProduct.getPrice() * orderProductInput.getQuantity();
        }
        return price;
    }

    public Map<String, Product> getProductsFromCatalog(OrderInput orderInput) {
        Map<String, Product> catalogProducts = new HashMap<>();
        orderInput.getProducts().stream().forEach(product -> {
            catalogProducts.put(product.getProductId(), catalogServiceClient.getProduct(product.getProductId()));
        });
        return catalogProducts;
    }

    private boolean isValidAddress(OrderInput orderInput) {
        Address address = customerServiceClient.getAddress(orderInput.getCustomerId(), orderInput.getAddressId());
        return address != null;
    }
}
