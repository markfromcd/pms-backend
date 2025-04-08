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
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)//save perfomance, only fetch when needing the cart object
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;

    @ManyToOne(fetch = FetchType.EAGER)// always want to get the product infos
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    // Constructor with product and quantity
    public CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Calculate Subtotal, transient, no need to write to db
    @Transient
    public Long getSubtotal() {
        return product.getPrice() * quantity;
    }

    // Calculate discounted subtotal
    @Transient
    public Long getDiscountedSubtotal() {
        return product.getDiscountPrice() * quantity;
    }
}
