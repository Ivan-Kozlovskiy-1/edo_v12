package com.education.service.nomenclature;

import com.education.model.dto.NomenclatureDto;
import com.education.service.BaseInterface;

import java.util.List;

public interface NomenclatureService extends BaseInterface<NomenclatureDto> {

    NomenclatureDto save(NomenclatureDto nomenclature);

    void moveToArchive(Long id);

    NomenclatureDto findById(Long id);

    List<NomenclatureDto> findAllById(List<Long> list);

    NomenclatureDto findByIdNotArchived(Long id);

    List<NomenclatureDto> findAllByIdNotArchived(List<Long> list);

    List<NomenclatureDto> findByIndex(String index);

    String getNumberFromTemplate(NomenclatureDto nomenclatureDto);

}
