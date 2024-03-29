package com.education.controller;

import com.education.model.dto.FacsimileDto;
import com.education.model.dto.FilePoolDto;
import com.education.service.facsimile.FacsimileService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Никита Бадеев
 *
 * Service Rest controller for Facsimile
 */
@RestController
@RequestMapping("/api/service/facsimile")
@AllArgsConstructor
@Log
@ApiModel("Контроллер эдо-сервиса для сущности Facsimile")
public class FacsimileController {

    @ApiModelProperty("Сервис для контроллера")
    private FacsimileService facsimileService;

    /**
     * Method for saving Facsimile in file-storage
     *
     * @param multipartFile - file for save
     * @return Facsimile as FilePoolDto
     */
    @ApiOperation("Сохранить факсимиле")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "File successfully added."),
            @ApiResponse(code = 404, message = "Not found - The file was not found")
    })
    @PostMapping()
    public ResponseEntity<FilePoolDto> saveFacsimile(@RequestPart("facsimile") MultipartFile multipartFile) {

        log.info("Request for saving Facsimile");
        if (!(facsimileService.isValidate(multipartFile))) {
            return new ResponseEntity(
                    "Facsimile should be jpg or png and should less than 100x100px", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(facsimileService.saveAsFile(multipartFile));
    }

    /**
     * Method for saving facsimile as Entity in DB
     *
     * @param jsonFile employee and others
     * @return facsimileDTO
     */
    @ApiOperation("Сохранить сущность факсимиле")
    @PostMapping("/")
    public ResponseEntity<FacsimileDto> saveFacsimileEntity(@RequestBody String jsonFile) {
        log.info("Request for saving facsimile entity");
        return ResponseEntity.ok().body(facsimileService.save(jsonFile));
    }

    /**
     * Method for archiving/unarchivig facsimile
     *
     * @param jsonFile data with id Facsimile and boolean isArchived
     * @return FacsimileDto
     */
    @ApiOperation("Архивировать факсимиле")
    @DeleteMapping("/archive")
    public ResponseEntity<FacsimileDto> archiveFacsimile(@RequestBody String jsonFile) {
        log.info("Request to archive facsimile");
        return ResponseEntity.ok().body(facsimileService.archiveFacsimile(jsonFile));
    }
}
