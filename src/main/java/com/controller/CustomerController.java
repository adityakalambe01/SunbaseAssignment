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
@RequestMapping(value = "/api")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customer-list")
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
    @PostMapping(value = "/create-customer")
    public boolean addNewCustomer(Customer customer) {
        return customerService.addNewCustomer(customer)!=null;
    }

    @PutMapping(value = "/update-customer/{id}")
    public boolean updateCustomer(@PathVariable("id") String customerId, Customer customer) {
        return customerService.udateExistingCustomer(customerId, customer)!=null;
    }

    @DeleteMapping("{id}")
    public boolean deleteCustomer(@PathVariable("id") String customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @PostMapping("/customer-sync")
    public void syncCustomers() {
        final String apiUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
        String bearerToken = "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";

        customerService.callExternalApi(apiUrl, bearerToken);
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
                StringBuffer firstName = new StringBuffer(firstNameArray[new Random().nextInt(length)]);
                StringBuffer lastName = new StringBuffer(lastNameArray[new Random().nextInt(length)]);
                Customer customer = new Customer(
                        UUID.randomUUID().toString(),
                        firstName.toString(),
                        lastName.toString(),
                        street[new Random().nextInt(length)],
                        address[new Random().nextInt(length)],
                        city[new Random().nextInt(length)],
                        state[new Random().nextInt(length)],
                        firstName.toString()+lastName.toString()+new Random().nextInt(10000)+"@gmail.com",
                        String.valueOf(new Random().nextLong(15000))
                );
                customerService.addNewCustomer(customer);
            }
        }
    }
}
