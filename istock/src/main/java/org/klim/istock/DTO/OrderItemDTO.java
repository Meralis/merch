package org.klim.istock.DTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderItemDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_seq")
    @SequenceGenerator(name = "order_item_id_seq", sequenceName = "order_item_id_seq")
    private Long orderItemId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private ProductDTO product;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private OrderDTO order;

    private int quantity;
    private int amount;
}
