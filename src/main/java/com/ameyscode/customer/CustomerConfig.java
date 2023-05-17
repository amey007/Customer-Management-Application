package com.ameyscode.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class CustomerConfig {
    //  This class is used for configuring data when we start the application.
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            Customer Alex = new Customer(
                    1,
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(1998, Month.FEBRUARY,7)
            );

            Customer James = new Customer(
                    5,
                    "James",
                    "james@gmail.com",
                    LocalDate.of(1994,Month.MAY, 12)
            );

            customerRepository.saveAll(List.of(Alex, James));
        };
    }
}
