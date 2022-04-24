package com.tomorrow.ParkingLotSystem.repository;

import com.tomorrow.ParkingLotSystem.domain.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, UUID> {

    Owner findByCpf(String cpf);
}
