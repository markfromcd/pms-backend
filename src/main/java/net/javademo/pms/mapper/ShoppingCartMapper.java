package net.javademo.pms.mapper;

import net.javademo.pms.entity.CartItem;
import net.javademo.pms.entity.ShoppingCart;
import net.javademo.pms.model.CartItemModel;
import net.javademo.pms.model.ShoppingCartModel;

import java.util.stream.Collectors;

public class ShoppingCartMapper {
    public static ShoppingCartModel mapToShoppingCartModel(ShoppingCart cart) {
        ShoppingCartModel cartModel = new ShoppingCartModel();
        cartModel.setId(cart.getId());

        // Map cart items
        if (cartModel.getItems() != null && !cartModel.getItems().isEmpty()) {
            cartModel.setItems(cart.getItems().stream()
                    .map(CartItemMapper::mapToCartItemModel)
                            .collect(Collectors.toList()));
        }
        // Set totals
        cartModel.setTotalPrice(cart.getTotalPrice());
        cartModel.setDiscountedTotalPrice(cart.getDiscountedTotalPrice());

        return cartModel;
    }

    public static ShoppingCart mapToShoppingCart(ShoppingCartModel cartModel) {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartModel.getId());

        // Map cart items if any
        if (cartModel.getItems() != null && !cartModel.getItems().isEmpty()) {
            for (CartItemModel itemModel : cartModel.getItems()) {
                CartItem item = CartItemMapper.mapToCartItem(itemModel);
                cart.addItem(item);
            }
        }
        return cart;
    }
}
