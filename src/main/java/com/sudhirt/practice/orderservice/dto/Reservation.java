package com.sudhirt.practice.orderservice.dto;

import com.sudhirt.practice.orderservice.constant.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {

    private String id;
    private String orderId;
    private Long quantity;
    private ReservationStatus status;
}
