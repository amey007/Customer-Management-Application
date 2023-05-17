package com.ameyscode.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        // Clients request is a JSON body(RequestBody) that is mapped to NewCustomerRequest class
        customerService.addCustomer(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerService.deleteCustomer(id);
    }

    record UpdateCustomerRequest(String email, Integer age){}

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id,
                               @RequestParam (required = false) String name,
                               @RequestParam (required = false) String email,
                               @RequestParam (required = false)LocalDate dob ) {
        // Clients request is a JSON body(RequestBody) that is mapped to NewCustomerRequest class
        customerService.updateCustomer(id, name, email, dob);
    }
}
