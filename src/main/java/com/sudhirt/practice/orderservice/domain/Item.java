package com.sudhirt.practice.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sudhirt.practice.orderservice.constant.FulfillmentStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class Item {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Column(nullable = false)
    private String productId;
    @Column(nullable = false)
    private long quantity;
    @Column(nullable = false)
    private Double perUnitCost;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FulfillmentStatus fulfillmentStatus;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
