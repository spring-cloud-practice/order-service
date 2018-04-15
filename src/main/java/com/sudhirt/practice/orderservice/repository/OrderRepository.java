package com.sudhirt.practice.orderservice.repository;

import com.sudhirt.practice.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
