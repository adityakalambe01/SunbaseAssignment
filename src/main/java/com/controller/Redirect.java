package com.controller;

import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/user")
public class Redirect {
    @Autowired
    private CustomerController customerController;


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
        model.addAttribute("allCustomers", customerController.getAllCustomers(pageNumber,pageSize));
        return "customerList";
    }

    @GetMapping(value = "/add-customer")
    public String addCustomer(Model model){
        return "addCustomer";
    }

    @GetMapping(value = "/sync")
    public String sync(Model model){
        customerController.syncCustomers();
        return "Done";
    }
}
