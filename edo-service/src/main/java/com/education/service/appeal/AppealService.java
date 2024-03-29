package com.education.service.appeal;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.model.dto.QuestionDto;
import com.education.service.BaseInterface;

import java.util.List;

public interface AppealService  extends BaseInterface<AppealDto> {
    AppealDto save(AppealDto appeal);

    void moveToArchive(Long id);

    AppealDto findById(Long id);

    List<AppealDto> findAllById(Iterable<Long> ids);

    AppealDto findByIdNotArchived(Long id);

    List<AppealDto> findAllByIdNotArchived(Iterable<Long> ids);

    List<AppealAbbreviatedDto> findAllByIdEmployee(Long startIndex, Long amount);

    AppealDto findByQuestion(QuestionDto questionDto);

    AppealDto findAppealByResolutionId(Long resolutionId);

    void moveToNewOrRegistered(Long id, String appealStatus);

    void moveToUnderConsideration(Long id);

    void markMailIsSent(Long id);
}