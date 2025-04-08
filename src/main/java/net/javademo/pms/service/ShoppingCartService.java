package net.javademo.pms.service;

import net.javademo.pms.entity.CartItem;
import net.javademo.pms.model.CartItemModel;
import net.javademo.pms.model.ShoppingCartModel;

public interface ShoppingCartService {
    ShoppingCartModel getCartById(Long cartId);

    ShoppingCartModel getCartByCustomerId(Long customerId);

    ShoppingCartModel addItemToCart(Long cartId, Long productId, Integer quantity);

    ShoppingCartModel updateCartItem(Long cartId, Long itemId, Integer quantity);

    ShoppingCartModel removeItemFromCart(Long cartId, Long itemId);

    void clearCart(Long cartId);

    CartItemModel getCartItem(Long cartId, Long itemId);
}
