package org.klim.istock;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double price ;

    @Column(nullable = false)
    private String category;

    private String description;

    @Lob
    private Blob image;

    public Product() {
    }

    public Product(Long id, String title, Double price, String category, String description) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
