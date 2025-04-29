package net.javademo.pms.controller;

import lombok.AllArgsConstructor;
import net.javademo.pms.model.CartItemModel;
import net.javademo.pms.model.ShoppingCartModel;
import net.javademo.pms.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:4200",
        "http://pms-alb-572541170.us-east-2.elb.amazonaws.com"
})
public class ShoppingCartController {

    private ShoppingCartService cartService;

    // Get cart by ID
    @GetMapping("{cartId}")
    public ResponseEntity<ShoppingCartModel> getCartById(@PathVariable Long cartId) {
        ShoppingCartModel cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    // Get cart by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ShoppingCartModel> getCartByCustomerId(@PathVariable Long customerId) {
        ShoppingCartModel cart = cartService.getCartByCustomerId(customerId);
        return ResponseEntity.ok(cart);
    }

    // Add item to cart
    @PostMapping("{cartId}/items")
    public ResponseEntity<ShoppingCartModel> addItemToCart(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        ShoppingCartModel updatedCart = cartService.addItemToCart(cartId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    // Update cart item quantity
    @PutMapping("{cartId}/items/{itemId}")
    public ResponseEntity<ShoppingCartModel> updateCartItem(
            @PathVariable Long cartId,
            @PathVariable Long itemId,
            @RequestParam Integer quantity) {
        ShoppingCartModel updatedCart = cartService.updateCartItem(cartId, itemId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    // Remove item from cart
    @DeleteMapping("{cartId}/items/{itemId}")
    public ResponseEntity<ShoppingCartModel> removeItemFromCart(
            @PathVariable Long cartId,
            @PathVariable Long itemId) {
        ShoppingCartModel updatedCart = cartService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.ok(updatedCart);
    }

    // Clear cart
    @DeleteMapping("{cartId}/items")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

    // Get specific cart item
    @GetMapping("{cartId}/items/{itemId}")
    public ResponseEntity<CartItemModel> getCartItem(
            @PathVariable Long cartId,
            @PathVariable Long itemId) {
        CartItemModel item = cartService.getCartItem(cartId, itemId);
        return ResponseEntity.ok(item);
    }
}