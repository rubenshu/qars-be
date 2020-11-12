package com.example.qars.service;

import com.example.qars.dao.EstablishmentRepository;
import com.example.qars.entity.Establishment;
import com.example.qars.entity.Location;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstablishmentService extends BaseService<Establishment> {

    private EstablishmentRepository repository;

    public EstablishmentService(EstablishmentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional
    public List<Establishment> getEstablishmentsByLocation(Location location) {
        return this.repository.getEstablishmentsByLocation(location.getId());
    }
}
