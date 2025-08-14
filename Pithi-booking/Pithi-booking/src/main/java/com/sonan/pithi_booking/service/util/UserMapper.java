package com.sonan.pithi_booking.service.util;

import com.sonan.pithi_booking.dto.UserDTO;
import com.sonan.pithi_booking.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);
    User toEntity(UserDTO userDto);

}