package com.service;

import com.model.Customer;
import com.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //Add Customer
    public Customer addNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //Update Customer
    public Customer udateExistingCustomer(String customerId, Customer browserCustomer) {
        Customer dbCustomer = this.getCustomerById(customerId);
        if (dbCustomer == null) {
            return null;
        }

        dbCustomer.setFirst_name(
                browserCustomer.getFirst_name()
        );
        dbCustomer.setLast_name(
                browserCustomer.getLast_name()
        );
        dbCustomer.setStreet(
                browserCustomer.getStreet()
        );
        dbCustomer.setAddress(
                browserCustomer.getAddress()
        );
        dbCustomer.setCity(
                browserCustomer.getCity()
        );
        dbCustomer.setState(
                browserCustomer.getState()
        );
        dbCustomer.setEmail(
                browserCustomer.getEmail()
        );
        dbCustomer.setPhone(
                browserCustomer.getPhone()
        );
        return customerRepository.save(dbCustomer);
    }

    //Customer by ID
    public Customer getCustomerById(String customerId) {
        if (customerRepository.existsById(customerId))
            return customerRepository.findById(customerId).get();
        return null;
    }

    //All Customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //Delete Customer
    public Boolean deleteCustomer(String customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }

        return false;
    }
}
