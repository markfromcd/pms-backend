package net.javademo.pms.mapper;

import net.javademo.pms.entity.Customer;
import net.javademo.pms.entity.ShoppingCart;
import net.javademo.pms.model.CustomerModel;

public class CustomerMapper {
    public static CustomerModel mapToCustomerModel(Customer customer) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customer.getId());
        customerModel.setName(customer.getName());
        customerModel.setEmail(customer.getEmail());
        customerModel.setAddress(customer.getAddress());
        customerModel.setPhone(customer.getPhone());

        //check if shopping cart exists
        if (customer.getCart() != null) {
            customerModel.setCart(ShoppingCartMapper.mapToShoppingCartModel(customer.getCart()));
        }
        return customerModel;
    }

    public static Customer mapToCustomer(CustomerModel customerModel) {
        Customer customer = new Customer();
        customer.setId(customerModel.getId());
        customer.setName(customerModel.getName());
        customer.setEmail(customerModel.getEmail());
        customer.setAddress(customerModel.getAddress());
        customer.setPhone(customerModel.getPhone());
        //create cart if needed
        if (customer.getCart() == null) {
            customer.setCart(new ShoppingCart());
        }
        return customer;
    }

}
