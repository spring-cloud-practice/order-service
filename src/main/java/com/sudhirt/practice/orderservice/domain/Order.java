package com.sudhirt.practice.orderservice.domain;

import com.sudhirt.practice.orderservice.constant.FulfillmentStatus;
import com.sudhirt.practice.orderservice.constant.OrderStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class Order {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Column(nullable = false)
    private String customerId;
    @Column(nullable = false)
    private String addressId;
    @Column(nullable = false)
    private Double orderPrice;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(nullable = false)
    private Date orderDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FulfillmentStatus fulfillmentStatus;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<Item> items;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<Fulfillment> fulfillmentDetails;

    public void add(Item item) {
        if(items == null) {
            items = new ArrayList<>();
        }
        item.setOrder(this);
        items.add(item);
    }
}
