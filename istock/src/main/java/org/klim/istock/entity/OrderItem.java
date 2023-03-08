package org.klim.istock.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_seq")
    @SequenceGenerator(name = "order_item_id_seq", sequenceName = "order_item_id_seq", allocationSize = 1)
    private Integer orderItemId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private Integer quantity;
    private Integer amount;
}
