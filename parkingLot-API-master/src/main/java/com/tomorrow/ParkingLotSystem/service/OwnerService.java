package com.tomorrow.ParkingLotSystem.service;

import com.tomorrow.ParkingLotSystem.domain.entities.Owner;
import com.tomorrow.ParkingLotSystem.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository repository;

    public List<Owner> findAll(){
        return repository.findAll();
    }

    public Owner findByCpf(String cpf){
        return repository.findByCpf(cpf);
    }

    public Owner insert(Owner obj){
        return repository.save(obj);
    }
}
