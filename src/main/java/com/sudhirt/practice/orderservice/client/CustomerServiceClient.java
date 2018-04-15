package com.sudhirt.practice.orderservice.client;

import com.sudhirt.practice.orderservice.dto.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gateway-service", path = "customer-service"/*, url = "http://localhost:8090/customer-service"*/)
public interface CustomerServiceClient {

    @GetMapping(value = "/customers/{customerId}/addresses/{addressId}")
    Address getAddress(@PathVariable("customerId") String customerId,
                       @PathVariable("addressId") String addressId);
}
