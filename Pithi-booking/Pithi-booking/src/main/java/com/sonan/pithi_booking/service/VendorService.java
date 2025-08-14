package com.sonan.pithi_booking.service;

import com.sonan.pithi_booking.entity.Vendor;

import java.util.List;

public interface VendorService {
    Vendor addVendor(Vendor vendor);
    Vendor updateVendor(Long id, Vendor updatedVendor);
    Vendor deleteVendor(Long id);
    List<Vendor> getAllVendors();
}
