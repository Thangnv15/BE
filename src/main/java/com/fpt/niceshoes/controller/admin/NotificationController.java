package com.fpt.niceshoes.controller.admin;

import com.fpt.niceshoes.infrastructure.common.ResponseObject;
import com.fpt.niceshoes.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/{id}")
    public ResponseObject getByAccount(@PathVariable Long id){
        return new ResponseObject(notificationService.getByAccount(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        notificationService.delete(id);
    }

    @DeleteMapping("/delete-all/{id}")
    public void deleteAll(@PathVariable Long id){
        notificationService.deleteAllByAccount(id);
    }
}
