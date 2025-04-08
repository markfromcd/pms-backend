package net.javademo.pms.service;

import lombok.AllArgsConstructor;
import net.javademo.pms.entity.CartItem;
import net.javademo.pms.entity.Product;
import net.javademo.pms.entity.ShoppingCart;
import net.javademo.pms.exception.ResourceNotFoundException;
import net.javademo.pms.mapper.CartItemMapper;
import net.javademo.pms.mapper.ShoppingCartMapper;
import net.javademo.pms.model.CartItemModel;
import net.javademo.pms.model.ShoppingCartModel;
import net.javademo.pms.repository.CartItemRepository;
import net.javademo.pms.repository.ProductRepository;
import net.javademo.pms.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;

    @Override
    public ShoppingCartModel getCartById(Long cartId) {
        ShoppingCart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart not found with id: " + cartId));

        return ShoppingCartMapper.mapToShoppingCartModel(cart);
    }

    @Override
    public ShoppingCartModel getCartByCustomerId(Long customerId) {
        ShoppingCart cart = cartRepository.findByCustomerId(customerId);

        if (cart == null) {
            throw new ResourceNotFoundException("Shopping cart not found for customer id: " + customerId);
        }

        return ShoppingCartMapper.mapToShoppingCartModel(cart);
    }

    @Override
    public CartItemModel getCartItem(Long cartId, Long itemId) {
        ShoppingCart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart not found with id: " + cartId));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + itemId));

        // Verify item belongs to the cart
        if (!item.getCart().getId().equals(cartId)) {
            throw new IllegalArgumentException("Item does not belong to the specified cart");
        }

        return CartItemMapper.mapToCartItemModel(item);
    }

    @Transactional
    public ShoppingCartModel addItemToCart(Long cartId, Long productId, Integer quantity) {
        ShoppingCart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart not found with id: " + cartId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        // Check if product is in stock
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Not enough stock available for product: " + product.getName());
        }

        // Check if item already exists in cart
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            // Update quantity of existing item
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            // Add new item to cart
            CartItem newItem = new CartItem(product, quantity);
            cart.addItem(newItem);
            cartItemRepository.save(newItem);
        }

        // Update cart
        ShoppingCart updatedCart = cartRepository.save(cart);
        return ShoppingCartMapper.mapToShoppingCartModel(updatedCart);
    }

    @Override
    @Transactional
    public ShoppingCartModel updateCartItem(Long cartId, Long itemId, Integer quantity) {
        ShoppingCart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart not found with id: " + cartId));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + itemId));

        // Verify item belongs to the cart
        if (!item.getCart().getId().equals(cartId)) {
            throw new IllegalArgumentException("Item does not belong to the specified cart");
        }

        // Check if product is in stock
        if (item.getProduct().getStock() < quantity) {
            throw new IllegalArgumentException("Not enough stock available for product: " + item.getProduct().getName());
        }

        // Update quantity
        item.setQuantity(quantity);
        cartItemRepository.save(item);

        // Get updated cart
        ShoppingCart updatedCart = cartRepository.findById(cartId).get();
        return ShoppingCartMapper.mapToShoppingCartModel(updatedCart);
    }

    @Override
    @Transactional
    public ShoppingCartModel removeItemFromCart(Long cartId, Long itemId) {
        ShoppingCart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart not found with id: " + cartId));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + itemId));

        // Verify item belongs to the cart
        if (!item.getCart().getId().equals(cartId)) {
            throw new IllegalArgumentException("Item does not belong to the specified cart");
        }

        // Remove item from cart
        cart.removeItem(item);
        cartItemRepository.delete(item);

        // Update cart
        ShoppingCart updatedCart = cartRepository.save(cart);
        return ShoppingCartMapper.mapToShoppingCartModel(updatedCart);
    }

    @Override
    @Transactional
    public void clearCart(Long cartId) {
        ShoppingCart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping cart not found with id: " + cartId));

        // Remove all items
        cart.getItems().clear();
        cartRepository.save(cart);
    }

}