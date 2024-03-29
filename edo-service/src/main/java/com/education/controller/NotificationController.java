package com.education.controller;

import com.education.model.dto.NotificationDto;
import com.education.service.notification.Feign.NotificationFeignClient;
import com.education.service.notification.NotificationService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("api/service/notification")
@AllArgsConstructor
@Log
@ApiModel("Контроллер репозитория для сущности Notification")
public class NotificationController {

    private final NotificationFeignClient feignClient;


    @ApiOperation("Получить сущность Notification по id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Notification was successfully found"),
            @ApiResponse(code = 404, message = "Notification was not found")})
    @GetMapping("/{id}")
    public NotificationDto getNotificationById(@PathVariable long id) {
        log.log(Level.INFO, "Получен запрос на поиск сущности с id = {0}", id);
        return feignClient.findById(id);
    }


    @ApiOperation("Получить несколько сущностей Notification по id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Notification was successfully found"),
            @ApiResponse(code = 404, message = "Employee was not found")})
    @GetMapping
    public List<NotificationDto> findAllNotificationsById(@RequestParam("ids") List<Long> ids) {
        log.log(Level.INFO, "Получен запрос на поиск сущностей с id = {0}", ids);
        return feignClient.findAllById(ids);
    }

    @ApiOperation("Сохранить сущность notification")
    @ApiResponse(code = 201, message = "Notification was saved")
    @PostMapping
    public HttpStatus saveNotification(@RequestBody NotificationDto notification) {
        log.log(Level.INFO, "Получен запрос на сохранение сущности - {0}", notification);
        feignClient.save(notification);
        return HttpStatus.CREATED;
    }
    @ApiOperation("Удалить сущность notification")
    @ApiResponse(code = 200, message = "Notification was deleted")
    @DeleteMapping("/{id}")
    public HttpStatus deleteNotification(@PathVariable long id) {
        log.log(Level.INFO, "Получен запрос на удаление сущности с id + {0}", id);
        feignClient.delete(id);
        return HttpStatus.OK;
    }
}
