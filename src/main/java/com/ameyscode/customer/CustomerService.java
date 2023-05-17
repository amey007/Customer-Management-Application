package com.ameyscode.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        // Clients request is a JSON body(RequestBody) that is mapped to NewCustomerRequest class
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

        if (customerOptional.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id){
        boolean existCust = customerRepository.existsById(id);
        if (!existCust){
            throw new IllegalStateException("Customer with id "+id+ " does not exist");
        }
        customerRepository.deleteById(id);
    }

    @Transactional
    public void updateCustomer(Integer id, String name, String email, LocalDate dob) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Customer with id "+id+ " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(customer.getName(), name)){
            customer.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(customer.getEmail(), email)){
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            customer.setEmail(email);
        }

        if (dob != null && !Objects.equals(customer.getDob(), dob)){
            customer.setDob(dob);
        }

    }
}
