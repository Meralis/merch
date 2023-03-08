package org.klim.istock.entity;

import lombok.Data;
//import org.klim.istock.model.OrderStatus;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
    private int orderId;

    //    private OrderStatus status;
    private String comments;
    private LocalDateTime created;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

    private int total;
    private String deliveryAddress;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items;
}
