package com.sudhirt.practice.orderservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private String id;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zip;
}
