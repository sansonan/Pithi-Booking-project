package com.sonan.pithi_booking.controller;

import com.sonan.pithi_booking.dto.VendorDTO;
import com.sonan.pithi_booking.entity.Vendor;
import com.sonan.pithi_booking.service.VendorService;
import com.sonan.pithi_booking.service.util.VendorMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;
    @Autowired
    private VendorMapper vendorMapper;

    @PostMapping("/create")
    public ResponseEntity<VendorDTO> addVendor(@Valid @RequestBody VendorDTO dto) {
        Vendor entity = vendorMapper.toEntity(dto);


        entity = vendorService.addVendor(entity);
        return ResponseEntity.ok(vendorMapper.toDTO(entity));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<VendorDTO>> filterVendor() {
        List<Vendor> allVendors = vendorService.getAllVendors();
        List<VendorDTO> dtos = allVendors.stream()
                .map(vendorMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

}
