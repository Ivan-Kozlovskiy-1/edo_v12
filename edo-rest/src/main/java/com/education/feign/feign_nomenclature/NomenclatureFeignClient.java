package com.education.feign.feign_nomenclature;

import com.education.feign.AbstractFeign;
import com.education.model.dto.NomenclatureDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс NomenclatureFeignClient предоставляет методы для отправки запросов в edo-service
 * через FeignClient.
 */
@FeignClient(name = "edo-service", path = "/api/service", qualifiers = "nomenclatureFeignClient")
public interface NomenclatureFeignClient extends AbstractFeign<NomenclatureDto> {
    @PostMapping("/nomenclature/")
    NomenclatureDto saveNomenclature(@RequestBody NomenclatureDto nomenclature);

    @GetMapping("/nomenclature/{id}")
    NomenclatureDto findByIdNomenclature(@PathVariable("id") Long id);

    @PostMapping("/nomenclature/findAll")
    List<NomenclatureDto> findAllByIdNomenclature(@RequestBody List<Long> ids);

    @GetMapping("/nomenclature/notArchived/{id}")
    NomenclatureDto findByIdNotArchivedNomenclature(@PathVariable("id") Long id);

    @PostMapping("/nomenclature/notArchived")
    List<NomenclatureDto> findAllByIdNotArchivedNomenclature(@RequestBody List<Long> ids);

    @PatchMapping("/nomenclature/archived/{id}")
    void moveToArchiveNomenclature(@PathVariable("id") Long id);

    @PatchMapping("/nomenclature/archived/{id}")
    void changeArchiveStatusNomenclature(@PathVariable("id") Long id, @RequestParam boolean archive);

    @GetMapping("/nomenclature/search/")
    List<NomenclatureDto> findByIndex(@RequestParam("index") String index);

}
