package org.klim.istock.DTO;
import lombok.Data;
//import org.klim.ishop.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrderDTO {
    private int orderId;
//    private OrderStatus status;
    private String comments;
    private LocalDateTime created;
    private ClientDTO client;
    private int total;
    private String deliveryAddress;
    private Set<OrderItemDTO> items;
}
