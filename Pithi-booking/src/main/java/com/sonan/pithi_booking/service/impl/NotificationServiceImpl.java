package com.sonan.pithi_booking.service.impl;
import com.sonan.pithi_booking.dto.NotificationDTO;
import com.sonan.pithi_booking.entity.Notification;
import com.sonan.pithi_booking.repository.NotificationRepository;
import com.sonan.pithi_booking.service.NotificationService;
import com.sonan.pithi_booking.service.util.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = mapper.toEntity(notificationDTO);
        notification.setIsRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        return mapper.toDto(repository.save(notification));
    }

    @Override
    public NotificationDTO getNotificationById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getNotificationsByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO updateNotification(Long id, NotificationDTO dto) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setMessage(dto.getMessage());
        notification.setType(dto.getType());
        notification.setIsRead(dto.getIsRead());

        return mapper.toDto(repository.save(notification));
    }

    @Override
    public void deleteNotification(Long id) {
        repository.deleteById(id);
    }
}
