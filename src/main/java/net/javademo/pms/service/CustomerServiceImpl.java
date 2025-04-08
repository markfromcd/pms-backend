package net.javademo.pms.service;

import lombok.AllArgsConstructor;
import net.javademo.pms.entity.Customer;
import net.javademo.pms.entity.ShoppingCart;
import net.javademo.pms.exception.ResourceNotFoundException;
import net.javademo.pms.mapper.CustomerMapper;
import net.javademo.pms.model.CustomerModel;
import net.javademo.pms.repository.CustomerRepository;
import net.javademo.pms.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public CustomerModel createCustomer(CustomerModel customerModel) {
        // Check if email already exists
        if (customerRepository.existsByEmail(customerModel.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + customerModel.getEmail());
        }

        Customer customer = CustomerMapper.mapToCustomer(customerModel);

        // Create a new shopping cart for the customer if not exists
        if (customer.getCart() == null) {
            ShoppingCart cart = new ShoppingCart();
            cart.setCustomer(customer);
            customer.setCart(cart);
        }

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerModel(savedCustomer);
    }

    @Override
    public CustomerModel getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        return CustomerMapper.mapToCustomerModel(customer);
    }

    @Override
    public CustomerModel getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));

        return CustomerMapper.mapToCustomerModel(customer);
    }

    @Override
    public List<CustomerModel> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(CustomerMapper::mapToCustomerModel)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerModel updateCustomer(Long customerId, CustomerModel updatedCustomerModel) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        // Check if email is being changed and if it already exists
        if (!customer.getEmail().equals(updatedCustomerModel.getEmail()) &&
                customerRepository.existsByEmail(updatedCustomerModel.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + updatedCustomerModel.getEmail());
        }

        customer.setName(updatedCustomerModel.getName());
        customer.setEmail(updatedCustomerModel.getEmail());
        customer.setAddress(updatedCustomerModel.getAddress());
        customer.setPhone(updatedCustomerModel.getPhone());

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerModel(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        customerRepository.delete(customer);
    }

    @Override
    public CustomerModel patchCustomer(Long customerId, Map<String, Object> fields) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        fields.forEach((key, value) -> {
            switch (key) {
                case "name":
                    customer.setName((String) value);
                    break;
                case "email":
                    String newEmail = (String) value;
                    // Check if email is being changed and if it already exists
                    if (!customer.getEmail().equals(newEmail) &&
                            customerRepository.existsByEmail(newEmail)) {
                        throw new IllegalArgumentException("Email already exists: " + newEmail);
                    }
                    customer.setEmail(newEmail);
                    break;
                case "address":
                    customer.setAddress((String) value);
                    break;
                case "phone":
                    customer.setPhone((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown field: " + key);
            }
        });

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerModel(savedCustomer);
    }
}