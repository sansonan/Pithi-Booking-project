package com.sonan.pithi_booking.service.util;

import com.sonan.pithi_booking.dto.VendorDTO;
import com.sonan.pithi_booking.entity.Vendor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface VendorMapper {
    VendorDTO toDTO(Vendor vendor);
    Vendor toEntity(VendorDTO dto);
}
