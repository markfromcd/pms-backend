package net.javademo.pms.controller;

import lombok.AllArgsConstructor;
import net.javademo.pms.model.CustomerModel;
import net.javademo.pms.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    // Create customer REST API
    @PostMapping
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customerModel) {
        CustomerModel savedCustomer = customerService.createCustomer(customerModel);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    // Get customer by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("id") Long customerId) {
        CustomerModel customerModel = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerModel);
    }

    // Get customer by email REST API
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerModel> getCustomerByEmail(@PathVariable("email") String email) {
        CustomerModel customerModel = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(customerModel);
    }

    // Get all customers REST API
    @GetMapping
    public ResponseEntity<List<CustomerModel>> getAllCustomers() {
        List<CustomerModel> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Update customer REST API
    @PutMapping("{id}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable("id") Long customerId,
                                                        @RequestBody CustomerModel updatedCustomerModel) {
        CustomerModel customerModel = customerService.updateCustomer(customerId, updatedCustomerModel);
        return ResponseEntity.ok(customerModel);
    }

    // Delete customer REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer deleted successfully!");
    }

    // Patch customer REST API
    @PatchMapping("{id}")
    public ResponseEntity<CustomerModel> patchCustomer(@PathVariable("id") Long customerId,
                                                       @RequestBody Map<String, Object> fields) {
        CustomerModel customerModel = customerService.patchCustomer(customerId, fields);
        return ResponseEntity.ok(customerModel);
    }
}