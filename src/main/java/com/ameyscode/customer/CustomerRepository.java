package com.ameyscode.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Transforms into SQL to check if email exists
    Optional<Customer> findCustomerByEmail(String email);
}
