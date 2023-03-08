package org.klim.istock.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;
    private String title;
    private Integer price;
    private String description;
    private String imageUrl;
}



