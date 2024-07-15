package com.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Customer;
import com.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //Add Customer
    public Customer addNewCustomer(Customer customer) {
        customer.setUuid(UUID.randomUUID().toString());
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
    public List<Customer> getAllCustomers(Integer pageNumber, Integer pageSize) {
        return   customerRepository.findAll(PageRequest.of(pageNumber, pageSize))
                .getContent();
    }

    //Delete Customer
    public Boolean deleteCustomer(String customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }

        return false;
    }

    public Long getCountOfCustomers() {
        return customerRepository.count();
    }

    public void callExternalApi(){
        String apiUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
        String bearerToken = "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";
        StringBuilder response = new StringBuilder();
        List<Customer> customerList = null;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Bearer " + bearerToken);
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            conn.disconnect();

            ObjectMapper objectMapper = new ObjectMapper();
            customerList = objectMapper.readValue(response.toString(), new TypeReference<List<Customer>>() {});
        }catch (Exception e){

        }

        /*for (Customer customer : customerList) {
            System.out.println(customer);
        }*/

        if (customerList != null) {
            for (Customer customer : customerList)
                customerRepository.save(customer);
        }

    }
}
