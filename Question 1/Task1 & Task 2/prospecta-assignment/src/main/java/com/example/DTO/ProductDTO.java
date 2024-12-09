package com.example.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {
    private Long id; // Ignored in POST requests

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    private Double price;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    private String image;
    private RatingDTO rating; // Optional in POST

    public ProductDTO(){}

    public ProductDTO(Long id, String title, Double price, String description, String category, String image, RatingDTO rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RatingDTO getRating() {
        return rating;
    }

    public void setRating(RatingDTO rating) {
        this.rating = rating;
    }
}
