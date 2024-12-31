package org.example.servicecontrats.feignclients;





import org.example.common.models.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "servicenotifications")
public interface NotificationServiceClient {

    @PostMapping("/notifications")
    void envoyerNotification(@RequestBody NotificationRequest notificationRequest);
}

