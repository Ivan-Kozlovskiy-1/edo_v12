package com.education.service.nomenclature;

import com.education.entity.Nomenclature;
import com.education.model.dto.NomenclatureDto;
import com.education.service.BaseInterface;

import java.util.List;

/**
 * Представляет список операций над номенклатурой
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */

public interface NomenclatureService extends BaseInterface<NomenclatureDto> {

    /**
     * Сохраняет номенклатуру в БД
     *
     * @param nomenclature NomenclatureDto
     * @return NomenclatureDto
     */
    NomenclatureDto save(NomenclatureDto nomenclature);

    /**
     * Переводит номенклатуру в архив
     *
     * @param id Long
     */
    void moveToArchive(Long id);


    /**
     * Меняет архивный признак номенклатуры
     *
     * @param id Long
     * @param archive boolean
     */

    void changeArchiveStatus(Long id, boolean archive);


    /**
     * Предоставляет NomenclatureDto номенклатуры из БД по id
     *
     * @param id Long
     * @return Nomenclature Dto
     */
    NomenclatureDto findById(Long id);

    /**
     * Предоставляет список NomenclatureDto номенклатур из БД по id
     *
     * @param list List of id
     * @return List of NomenclatureDto
     */
    List<NomenclatureDto> findAllById(Iterable<Long> list);

    /**
     * Предоставляет не заархивированное NomenclatureDto номенклатуры из БД по id
     *
     * @param id Long
     * @return NomenclatureDto
     */
    NomenclatureDto findByIdNotArchived(Long id);

    /**
     * Предоставляет список не заархивированных NomenclatureDto номенклатур из БД по id
     *
     * @param list List of id
     * @return List of NomenclatureDto
     */
    List<NomenclatureDto> findAllByIdNotArchived(Iterable<Long> list);

    /**
     * Предоставляет список номенклатур из БД по индексу
     *
     * @return List of NomenclatureDto
     */
    List<Nomenclature> findByIndex(String index);
}
