package com.scaler.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class ProductResponseDTO {
    private UUID id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseDTO that = (ProductResponseDTO) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(category, that.category) && Objects.equals(description, that.description) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, category, description, image);
    }
}
