package com.tomorrow.ParkingLotSystem.repository;

import com.tomorrow.ParkingLotSystem.domain.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    Vehicle findByPlate(String plate);
}
