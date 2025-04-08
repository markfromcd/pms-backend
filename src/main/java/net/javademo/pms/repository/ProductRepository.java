package net.javademo.pms.repository;

import net.javademo.pms.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//dao, connect db, JpaRepository<Product, Long> Entity type and primary key's type
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //JPA already provided CRUD oprations, no need to add other operations for product management
}
