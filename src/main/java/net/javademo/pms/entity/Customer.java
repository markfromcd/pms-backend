package net.javademo.pms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    private String phone;

//    // One customer can have many orders
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)//cascade: any edit of customers will be cascaded to its orders,
//    // orphanRemoval: customer delete an order, it will be deleted by DB
//    private List<Order> orders = new ArrayList<>();

    // One customer has one cart
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")//customers table will have a foreign key which is refered the id in ShoppingCart table
    private ShoppingCart cart;
}
