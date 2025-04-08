package net.javademo.pms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One to many relationship with cartitems
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)//map the object in CartItem
    private List<CartItem> items = new ArrayList<>();

    // One to one relationship with customer (bidirectional)
    @OneToOne(mappedBy = "cart")
    private Customer customer;

    // Calculate total price
    @Transient // function, write Transient just for explicitly showing it will not be saved in db
    public Long getTotalPrice() {
        return items.stream()
                .mapToLong(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    @Transient
    public Long getDiscountedTotalPrice() {
        return items.stream()
                .mapToLong(item -> item.getProduct().getDiscountPrice() * item.getQuantity())
                .sum();
    }

    // Add item to cart
    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

    // Remove item from cart
    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);//prevent orphan object
    }
}
