package com.education.controller;

import com.education.entity.Facsimile;
import com.education.model.dto.FacsimileDto;
import com.education.service.facsimile.FacsimileService;
import com.education.service.filepool.FilePoolService;
import com.education.util.Mapper.impl.FacsimileMapper;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;

@RestController
@RequestMapping("/api/repository/facsimile")
@AllArgsConstructor
@Log
@ApiModel("Controller for Facsimile")
public class FacsimileController {

    @ApiModelProperty("service")
    private final FacsimileService facsimileService;
    private final FilePoolService filePoolService;
    @ApiModelProperty("mapper")
    private final FacsimileMapper facsimileMapper;

    /**
     * Method for getting facsimile
     *
     * @param id is Id facsimile in DB
     */
    @ApiOperation(value = "Получить факсимиле по id", notes = "Returns facsimile by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The Facsimile was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FacsimileDto> getFacsimile(@PathVariable("id") Long id) {
        log.info("Request to get facsimile by id = " + id);

        var result = facsimileService.findById(id);
        if (result.isEmpty()) {
            log.warning("Facsimile not found");
        } else {
            log.info("Facsimile was found");
        }
        return result.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(facsimileMapper.toDto(result.get()), HttpStatus.OK);
    }

    /**
     * Method for saving Facsimile in DB
     *
     * @param facsimile Facsimile to save
     */
    @ApiOperation(value = "Сохранить факсимиле")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added")
    })
    @PostMapping("/")
    public ResponseEntity<FacsimileDto> saveFacsimile(@RequestBody Facsimile facsimile) {
        log.info("Request for saving facsimile");

        Facsimile facsimileSaved = facsimileService.saveFacsimile(facsimile);
        if (facsimileSaved == null) {
            log.warning("Facsimile couldn't be saved");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            log.info("Facsimile saved");
            return new ResponseEntity<>(facsimileMapper.toDto(facsimileSaved), HttpStatus.CREATED);
        }
    }

    @ApiOperation(value = "Архивация факсимиле")
    @DeleteMapping("/archive")
    public ResponseEntity<FacsimileDto> archiveFacsimile(@RequestBody Facsimile facsimile) {
        log.info("Request to archive/unarchive facsimile by id - " + facsimile.getId());

        facsimileService.moveInArchive(facsimile.getId(), facsimile.isArchived());
        filePoolService.moveToArchive(facsimile.getFile().getId(), facsimile.isArchived());
        FacsimileDto facsimileDTO = facsimileMapper.toDto(facsimile);
        facsimileDTO.setArchived(facsimile.isArchived());
        return new ResponseEntity<>(facsimileDTO, HttpStatus.OK);
    }

    @ApiOperation("Получить сущность Facsimile по employee_id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Facsimile was successfully found"),
            @ApiResponse(code = 404, message = "Facsimile was not found")})
    @GetMapping("/by-employee/{id}")
    public ResponseEntity<FacsimileDto> getFacsimileByEmployeeId(
            @PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос на поиск сущности с employee_id = {0}", id);
        FacsimileDto facsimileDto = facsimileMapper
                .toDto(facsimileService.findFacsimileByEmployeeId(id));
        log.log(facsimileDto != null
                        ? Level.INFO
                        : Level.WARNING
                , "Результат поиска: {0}", facsimileDto);
        return new ResponseEntity<>(facsimileDto
                , facsimileDto != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
