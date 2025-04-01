package net.javademo.pms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private Long price;
    private Integer stock;
    private String category;
    private String description;

    @Transient //This field will not be persisted in the database
    private double discountRate;

    // Constructor with category and description
    public Product(Long id, String name, Long price, Integer stock, String category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.description = description;
    }

    // Get discount price
    public Long getDiscountPrice() {
        if (discountRate > 0) {
            return Math.round(price * (1 - discountRate));
        }
        return price;
    }
}
