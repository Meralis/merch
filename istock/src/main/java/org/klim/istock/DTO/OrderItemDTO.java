package org.klim.istock.DTO;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Integer orderItemId;
    private Integer productId;
    private Integer quantity;
    private Integer amount;
}
