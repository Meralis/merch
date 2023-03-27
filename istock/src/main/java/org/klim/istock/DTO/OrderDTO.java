package org.klim.istock.DTO;

import lombok.Data;
import org.klim.istock.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Integer orderId;
    private OrderStatus status;
    private String comments;
    private LocalDateTime created;
    private ClientDTO client;
    private Integer total;
    private String deliveryAddress;
    private List<OrderItemDTO> items;
}
