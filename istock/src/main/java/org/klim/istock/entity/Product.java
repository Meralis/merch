package org.klim.istock.entity;

import javax.persistence.*;


@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String category;

    private String description;

    private String image;

    public Product() {
    }

    public Product(Long id, String title, Double price, String category, String description, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = "";
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

    public String getImage() {
        return image;
    }

    public void setImage(String imagePath) {
        this.image = imagePath;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
