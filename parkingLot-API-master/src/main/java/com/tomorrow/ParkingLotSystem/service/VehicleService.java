package com.tomorrow.ParkingLotSystem.service;

import com.tomorrow.ParkingLotSystem.domain.entities.Vehicle;
import com.tomorrow.ParkingLotSystem.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public List<Vehicle> findAll(){
        return repository.findAll();
    }

    public Vehicle findByPlate(String plate){
        return repository.findByPlate(plate);
    }

    public Vehicle insert(Vehicle vehicle){
        return repository.save(vehicle);
    }
}
