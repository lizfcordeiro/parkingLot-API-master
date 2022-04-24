package com.tomorrow.ParkingLotSystem.dto;

import com.tomorrow.ParkingLotSystem.domain.entities.Owner;
import com.tomorrow.ParkingLotSystem.domain.entities.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleDto {

    private String plate;
    private String model;
    private String brand;

    public VehicleDto(Vehicle vehicle) {
        this.plate = vehicle.getPlate();
        this.model = vehicle.getModel();
        this.brand = vehicle.getBrand();
    }

    public String getPlate() {
        return plate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public static List<VehicleDto> converter(List<Vehicle> vehicleList){
        return vehicleList.stream().map(VehicleDto::new).collect(Collectors.toList());
    }
}
