package com.tomorrow.ParkingLotSystem.resources;

import com.tomorrow.ParkingLotSystem.domain.entities.Owner;
import com.tomorrow.ParkingLotSystem.dto.OwnerDto;
import com.tomorrow.ParkingLotSystem.exceptions.ResourceNotFoundException;
import com.tomorrow.ParkingLotSystem.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/owners")
public class OwnerController {

    @Autowired
    private OwnerService service;

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAll(){
        List<Owner> ownerList = service.findAll();
        return ResponseEntity.ok(OwnerDto.converterOwner(ownerList));
    }

    @GetMapping("/filter")
    public ResponseEntity<OwnerDto> getByCpf(@RequestParam("cpf") String cpf){
        verifyIfOwnerExists(cpf);
        Owner obj = service.findByCpf(cpf);
        return ResponseEntity.ok().body(new OwnerDto(obj));
    }

    @PostMapping
    public ResponseEntity<OwnerDto> postOwner(@RequestBody @Valid Owner owner){
        Owner obj = service.insert(owner);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(owner.getCpf()).toUri();
        return ResponseEntity.created(uri).body(new OwnerDto(obj));
    }

    private void verifyIfOwnerExists(String cpf){
        if (service.findByCpf(cpf) == null){
            throw new ResourceNotFoundException("Owner not found for cpf: " + cpf);
        }
    }
}
