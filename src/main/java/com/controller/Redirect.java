package com.controller;

import com.model.Customer;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class Redirect {
    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerService customerService;


    @GetMapping(value = "/login")
    public String homePage(Model model){
        return "login";
    }

    @GetMapping(value = "/customers")
    public String customersPage(
            Model model,
            @RequestParam(value = "page",defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer pageSize
    ){
        List<Customer> customerList = customerController.getAllCustomers(pageNumber,pageSize);
        List<Long> pagesCount = new ArrayList<>();
        for (long i = 0; i < customerController.getCountOfCustomers()/10+1; i++) {
            pagesCount.add(i);
        }

        model.addAttribute("allCustomers", customerList);
        model.addAttribute("value", customerList.size()!=0);
        model.addAttribute("totalRecords", pagesCount);
        model.addAttribute("pageNumberUrl","/user/customers?page=");
        return "customerList";
    }

    @GetMapping(value = "/create-customer")
    public String addCustomer(Model model){
        model.addAttribute("FormMessage","Add Customer Details");
        model.addAttribute("customer", new Customer());
        model.addAttribute("SubmitButton","/user/add-customer");
        model.addAttribute("SubmitButtonText","Add Customer");

        return "addCustomer";
    }

    @GetMapping(value = "/sync")
    public String sync(Model model){
        customerController.syncCustomers();
        return customersPage(model,0,10);
    }

    @PostMapping(value = "/add-customer")
    public String addCustomer(Customer customer, Model model){
        System.out.println(customerController.addNewCustomer(customer));
        return customersPage(model, 0, 10);
    }

//    @PostMapping()

    @GetMapping(value = "/edit-customer")
    public String updateCustomerPage(String uuid, Model model){
//        model.addAttribute("customer",customerController.getCustomerById(uuid));
        model.addAttribute("customer",customerController.getCustomerById(uuid));
        model.addAttribute("FormMessage","Update Customer Details");
        model.addAttribute("SubmitButton","/user/update-customer");
        model.addAttribute("SubmitButtonText","Update Customer");
        return "addCustomer";
    }

    @PostMapping(value = "/update-customer")
    public String updateCustomer(Customer customer, Model model){
        System.out.println(
                customerController.updateCustomer(customer.getUuid(), customer)
        );
        return customersPage(model, 0, 10);
    }

    @PostMapping(value = "/delete-customer")
    public String deleteCustomer(String uuid, Model model){
        System.out.println(customerController.deleteCustomer(uuid));
        return customersPage(model, 0, 10);
    }
}
