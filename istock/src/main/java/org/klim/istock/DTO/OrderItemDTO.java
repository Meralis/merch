package org.klim.istock.DTO;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Integer orderItemId;
    private ProductDTO product;
    private OrderDTO order;
    private Integer quantity;
    private Integer amount;
}
