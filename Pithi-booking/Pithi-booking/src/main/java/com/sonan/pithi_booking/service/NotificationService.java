package com.sonan.pithi_booking.service;

import com.sonan.pithi_booking.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO notificationDTO);
    NotificationDTO getNotificationById(Long id);
    List<NotificationDTO> getAllNotifications();
    List<NotificationDTO> getNotificationsByUserId(Long userId);
    NotificationDTO updateNotification(Long id, NotificationDTO notificationDTO);
    void deleteNotification(Long id);
}
