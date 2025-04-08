package net.javademo.pms.model;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javademo.pms.entity.Product;
import net.javademo.pms.entity.ShoppingCart;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemModel {
    private Long id;
//    private ShoppingCart cart; no need to write this
    private ProductModel product;
    private Integer quantity;
    private Long subtotal;
    private Long discountedSubtotal;

    // ConstructorModel with just product and quantity (Actual operations in daily life)
    public CartItemModel(ProductModel product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
        this.discountedSubtotal = product.getDiscountedPrice() * quantity;
    }
}
