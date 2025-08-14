package com.sonan.pithi_booking.service.util;

import com.sonan.pithi_booking.dto.BookingDTO;
import com.sonan.pithi_booking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "status", target = "status") // enum to String
    BookingDTO toDTO(Booking booking);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "status", source = "status")
    Booking toEntity(BookingDTO dto);
}


