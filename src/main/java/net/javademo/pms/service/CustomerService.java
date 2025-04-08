package net.javademo.pms.service;

import net.javademo.pms.model.CustomerModel;
import net.javademo.pms.model.ProductModel;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    CustomerModel createCustomer(CustomerModel customerModel);

    CustomerModel getCustomerById(Long id);

    CustomerModel getCustomerByEmail(String email);

    List<CustomerModel> getAllCustomers();

    CustomerModel updateCustomer(Long customerId, CustomerModel updatedCustomerModel);

    void deleteCustomer(Long customerId);

    CustomerModel patchCustomer(Long customerId, Map<String, Object> fields);
}
