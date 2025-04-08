package net.javademo.pms.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javademo.pms.entity.CartItem;
import net.javademo.pms.entity.Customer;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartModel {
    private Long id;
    private List<CartItemModel> items = new ArrayList<>();
    private Long totalPrice;
    private Long discountedTotalPrice;
//    private CustomerModel customer; customer has already connect the shopping cart model

    // Constructor without items for simple creation, no need to connect to customer
    public ShoppingCartModel(Long id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.totalPrice = 0L;
        this.discountedTotalPrice = 0L;
    }
}
