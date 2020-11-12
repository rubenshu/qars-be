package com.example.qars.service;

import com.example.qars.dao.AdminRepository;
import com.example.qars.entity.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Blob;
import java.util.List;

@Service
public class AdminService extends BaseService<Customer> {
    private AdminRepository repository;

    public AdminService(AdminRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional
    public List<Customer> getLicenseRequests() {
        return this.repository.getLicenseRequests();
    }

    @Transactional
    public boolean licenseRequestResponse(int customerid, String response) {
        int changed = 0;

        if (response.equals("ACCEPT")) { changed = this.repository.acceptLicenseRequest(customerid); }
        if (response.equals("DECLINE")) { changed = this.repository.declineLicenseRequest(customerid); }


        return changed != 0;
    }
}
