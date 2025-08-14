package com.sonan.pithi_booking.service.util;

import com.sonan.pithi_booking.dto.ReviewDTO;
import com.sonan.pithi_booking.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public interface ReviewMapper {
    ReviewDTO toDto(Review review);
    Review toEntity(ReviewDTO dto);
}
