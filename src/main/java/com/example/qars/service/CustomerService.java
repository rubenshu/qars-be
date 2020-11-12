package com.example.qars.service;


import com.example.qars.dao.CustomerRepository;
import com.example.qars.entity.Customer;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Blob;


@Service
public class CustomerService extends BaseService<Customer> {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional
    public Customer loadCustomerById(int id) throws ChangeSetPersister.NotFoundException {
        Customer customer = repository.getCustomerById(id);
        if(customer != null) return customer;
        else throw new ChangeSetPersister.NotFoundException();
    }

    @Transactional
    public boolean uploadLicense(int id, Blob blob) {
        int customer = repository.uploadLicense(blob, id);
        return customer != 0;
    }

    @Transactional
    public int getCount() {
        return this.repository.getCount();
    }

}
