package com.education.controller;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.service.appeal.CreatingAppealService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/rest/appeal")
public class CreateAppealController {

    private final CreatingAppealService service;

    @ApiOperation(value = "Сохранение обращения")
    @PostMapping
    public ResponseEntity<AppealDto> createNewAppeal(@RequestBody AppealDto appeal) {
        AppealDto appealAfter = service.createAppeal(appeal);
        return new ResponseEntity<>(appealAfter, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Редактирование обращения")
    @PutMapping(value = "/edit")
    public ResponseEntity<AppealDto> editAppeal(@RequestBody AppealDto appeal) {
        AppealDto appealAfter = service.editAppeal(appeal);
        if (appealAfter == null) {
            log.warn("Сущность не существует");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appealAfter, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей Appeal для Employee creator (?startIndex=1&amount=1)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/appealsByEmployee/")
    public ResponseEntity<List<AppealAbbreviatedDto>> findByIdEmployee(@RequestParam("startIndex") Long startIndex,
                                                                       @RequestParam("amount") Long amount) {
        List<AppealAbbreviatedDto> appeal = service.findAllByIdEmployee(startIndex, amount);
        if (CollectionUtils.isEmpty(appeal)) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(appeal, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущности Appeal по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/appealById/{id}")
    public ResponseEntity<AppealDto> findById(@ApiParam("id") @PathVariable Long id) {
        AppealDto appeal = service.findById(id);
        if (appeal == null) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(appeal, HttpStatus.OK);
    }
}