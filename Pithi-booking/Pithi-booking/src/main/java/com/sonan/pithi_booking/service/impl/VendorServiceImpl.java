package com.sonan.pithi_booking.service.impl;

import com.sonan.pithi_booking.entity.User;
import com.sonan.pithi_booking.entity.Vendor;
import com.sonan.pithi_booking.repository.UserRepository;
import com.sonan.pithi_booking.repository.VendorRepository;
import com.sonan.pithi_booking.service.VendorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Vendor addVendor(Vendor vendor) {
        System.out.println(vendor.toString());

        if (vendor.getUser() == null || vendor.getUser().getId() == null) {
            throw new IllegalArgumentException("Vendor must be associated with a valid user");
        }

        // Optionally fetch the user to ensure it exists
        User user = userRepository.findById(vendor.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + vendor.getUser().getId()));

        vendor.setUser(user); // attach managed user entity

        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor updatedVendor) {
        Vendor exitVendor = new Vendor();
        exitVendor.setBusinessName(updatedVendor.getBusinessName());
        exitVendor.setDescription(updatedVendor.getDescription());
        exitVendor.setLocation(updatedVendor.getLocation());
        exitVendor.setProducts(updatedVendor.getProducts());
        return vendorRepository.save(exitVendor);
    }

    @Override
    public Vendor deleteVendor(Long id) {
        return null;
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
