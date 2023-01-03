package com.georgyi.notification;

import com.georgyi.clients.fraud.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
public class NotificationService{
    @Autowired
    private final NotificationRepository notificationRepository;
    public NotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }
    public void sendNotification(NotificationRequest notificationRequest){
        Notification notification = Notification.builder()
                .notificationId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .message(notificationRequest.message())
                .sender("Georgyi")
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}
