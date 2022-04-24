package com.tomorrow.ParkingLotSystem.resources;

import com.tomorrow.ParkingLotSystem.domain.entities.Owner;
import com.tomorrow.ParkingLotSystem.domain.entities.Vehicle;
import com.tomorrow.ParkingLotSystem.dto.VehicleDto;
import com.tomorrow.ParkingLotSystem.exceptions.ResourceNotFoundException;
import com.tomorrow.ParkingLotSystem.service.OwnerService;
import com.tomorrow.ParkingLotSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicle(){
        List<Vehicle> vehicleList = service.findAll();
        return ResponseEntity.ok(VehicleDto.converter(vehicleList));
    }

    @GetMapping("filter")
    public ResponseEntity<VehicleDto> getByPlate(@RequestParam("plate") String plate){
        verifyIfVehicleExists(plate);
        Vehicle obj = service.findByPlate(plate);
        return ResponseEntity.ok(new VehicleDto(obj));
    }

    @PostMapping
    public ResponseEntity<VehicleDto> postVehicle(@RequestBody @Valid Vehicle vehicle){
        vehicle.setOwnerVehicle(getOwner(vehicle));
        Vehicle obj = service.insert(vehicle);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{plate}").buildAndExpand(vehicle.getPlate()).toUri();
        return ResponseEntity.created(uri).body(new VehicleDto(obj));
    }

    private void verifyIfVehicleExists(String plate){
        if (service.findByPlate(plate) == null){
            throw new ResourceNotFoundException("Vehicle not found by plate: " + plate);
        }
    }

    private void verifyIfOwnerExists(String cpf){
        if (ownerService.findByCpf(cpf) == null){
            throw new ResourceNotFoundException("Owner not found for cpf: " + cpf);
        }
    }

    private Owner getOwner(Vehicle vehicle){
        String ownerCpf = vehicle.getOwnerCpf();
        verifyIfOwnerExists(ownerCpf);
        return ownerService.findByCpf(ownerCpf);
    }
}
