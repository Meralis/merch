package org.klim.istock.entity;

import lombok.Data;
import org.klim.istock.model.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
    private Integer orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String comments;
    private LocalDateTime created;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    private Integer total;
    private String deliveryAddress;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<OrderItem> items;
}
