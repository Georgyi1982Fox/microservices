package com.georgyi.notification;

import com.georgyi.clients.fraud.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@Slf4j
@RequestMapping("api/v1/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("new notification sending {}", notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }
}
