package net.javademo.pms.repository;

import net.javademo.pms.entity.CartItem;
import net.javademo.pms.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Find all items in a cart
    List<CartItem> findByCartId(Long cartId);

    // Find a specific product in a cart
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    void deleteByProduct(Product product);//new
}
