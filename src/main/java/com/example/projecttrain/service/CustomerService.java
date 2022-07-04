package com.example.projecttrain.service;

import com.example.projecttrain.Repository.CustomerRepository;
import com.example.projecttrain.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

        public List<Customer> getAllCustomer() {
            return customerRepository.findAll();
        }

        public  Customer getCustomerById(Long id) {
            return customerRepository.findById(id).orElse(null);
        }

        public Customer saveCustomer(Customer customer) {
            return customerRepository.save(customer);
        }

        public Customer updateCustomerById(Long id, Customer customer) {
            return customerRepository.save(customer);
        }
        public void deleteCustomerById(Long id) {
             customerRepository.deleteById(id);
        }

}
