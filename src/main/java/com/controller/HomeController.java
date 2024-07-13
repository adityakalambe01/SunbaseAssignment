package com.controller;

import com.model.Customer;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(
            @RequestParam(value = "page",defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "8", required = false) Integer pageSize
    ) {
        return this.customerService.getAllCustomers(pageNumber, pageSize);
    }

    @GetMapping("/current-user")
    public String getCurrentUser(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@RequestParam("id") String customerId) {
        return customerService.getCustomerById(customerId);
    }

    //Add Customer
    @PostMapping
    public boolean addNewCustomer(Customer customer) {
        return customerService.addNewCustomer(customer)!=null;
    }

    @PutMapping
    public boolean updateCustomer(String customerId, Customer customer) {
        return customerService.udateExistingCustomer(customerId, customer)!=null;
    }

    @DeleteMapping("{id}")
    public boolean deleteCustomer(@PathVariable("id") String customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @PostMapping("addDefault")
    private void addDefaultCustomer() {
        String[] firstNameArray = new String[]{"Aditya", "Sanket", "Alex", "Elsa", "Khushbu", "Priya"};
        String[] lastNameArray = new String[]{"Deo", "Kalambe", "Parsewar", "Sharma", "Leone", "Khan"};

        String[] street = new String[]{"Street 1", "Street 2", "Street 3", "Street 4", "Street 5", "Street 6"};
        String[] address = new String[]{"Address 1", "Address 2", "Address 3", "Address 4", "Address 5", "Address 6"};

        String[] city = new String[]{"City 1", "City 2", "City 3", "City 4", "City 5", "City 6"};
        String[] state = new String[]{"State 1", "State 2", "State 3", "State 4", "State 5", "State 6"};

        if (customerService.getCountOfCustomers()==0){
            int length = firstNameArray.length;
            for (int i = 0; i < 20; i++) {
                Customer customer = new Customer();

                customer.setUuid(UUID.randomUUID().toString());

                customer.setFirst_name(firstNameArray[new Random().nextInt(length)]);

                customer.setLast_name(lastNameArray[new Random().nextInt(length)]);

                customer.setStreet(street[new Random().nextInt(length)]);

                customer.setAddress(address[new Random().nextInt(length)]);

                customer.setCity(city[new Random().nextInt(length)]);

                customer.setState(state[new Random().nextInt(length)]);

                customer.setEmail(
                        customer.getFirst_name().toLowerCase()+customer.getLast_name().toLowerCase()+new Random().nextInt(10000)+"@gmail.com"
                );
                customer.setPhone(new Random().nextLong(15000));
                customerService.addNewCustomer(customer);
            }
        }
    }
}
