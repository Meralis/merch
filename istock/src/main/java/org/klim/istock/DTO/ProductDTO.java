package org.klim.istock.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer productId;
    private String title;
    private Integer price;
    private String description;
    private String category;
    private String imageLink;
}



