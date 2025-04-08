package net.javademo.pms.repository;

import net.javademo.pms.entity.Product;
import net.javademo.pms.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    // Find shopping cart by customer id
    ShoppingCart findByCustomerId(Long customerId);// it has object customer, so no need to add foreign key in shopping cart class
}
