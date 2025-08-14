package com.sonan.pithi_booking.service.util;

import com.sonan.pithi_booking.dto.BookingRequestDTO;
import com.sonan.pithi_booking.dto.BookingResponseDTO;
import com.sonan.pithi_booking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMappers {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "customer.id", target = "customerId")
    BookingResponseDTO toResponseDTO(Booking booking);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Booking fromRequestDTO(BookingRequestDTO dto);
}
