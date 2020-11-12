package com.example.qars.service;

import com.example.qars.dao.AddressRepository;
import com.example.qars.entity.Address;
import com.example.qars.entity.Customer;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService extends BaseService<Address> {
    public AddressRepository repository;

    public AddressService(AddressRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional
    public Address loadAddressById(int id) throws ChangeSetPersister.NotFoundException {
        Address address = repository.getAddressById(id);
        if(address != null) return address;
        else throw new ChangeSetPersister.NotFoundException();
    }

}
