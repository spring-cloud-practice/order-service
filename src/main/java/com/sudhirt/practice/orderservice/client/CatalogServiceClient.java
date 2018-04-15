package com.sudhirt.practice.orderservice.client;

import com.sudhirt.practice.orderservice.dto.Product;
import com.sudhirt.practice.orderservice.dto.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("catalog-service")
public interface CatalogServiceClient {

    @GetMapping(value = "/products/{productId}")
    Product getProduct(@PathVariable("productId") String productId);

    @PostMapping(value = "/products/{productId}/reservations")
    Product reserve(@PathVariable("productId") String productId, @RequestBody Reservation reservation);
}
