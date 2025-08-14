package com.sonan.pithi_booking.service.util;

import com.sonan.pithi_booking.dto.NotificationDTO;
import com.sonan.pithi_booking.entity.Notification;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDTO toDto(Notification notification);
    Notification toEntity(NotificationDTO dto);
}
