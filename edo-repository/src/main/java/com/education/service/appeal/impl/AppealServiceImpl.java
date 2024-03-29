package com.education.service.appeal.impl;

import com.education.entity.Appeal;
import com.education.model.dto.AppealDto;
import com.education.repository.AppealRepository;
import com.education.service.AbstractService;
import com.education.service.appeal.AppealService;
import com.education.util.Mapper.impl.AppealMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Slf4j
public class AppealServiceImpl extends AbstractService<AppealRepository, Appeal, AppealDto, AppealMapper> implements AppealService {

    final AppealRepository appealRepository;

    public AppealServiceImpl(AppealRepository repository, AppealMapper appealMapper, AppealRepository appealRepository) {
        super(repository, appealMapper);
        this.appealRepository = appealRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Appeal save(Appeal appeal) {
        appeal.setCreationDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return appealRepository.saveAndFlush(appeal);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToArchive(Long id) {
        appealRepository.moveToArchive(id);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findById(Long id) {
        return appealRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllById(Iterable<Long> ids) {
        return appealRepository.findAllById(ids);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findAppealByQuestionId(Long questionId) {
        return appealRepository.findAppealByQuestionId(questionId);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findAppealByResolutionId(Long resolutionId) {
        return appealRepository.findAppealByResolutionId(resolutionId);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findByIdNotArchived(Long id) {
        return appealRepository.findByIdNotArchived(id).orElse(null);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllByIdNotArchived(Iterable<Long> ids) {
        return appealRepository.findAllByIdNotArchived(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToUnderConsideration(Long resolutionId) {
        appealRepository.moveToUnderConsideration(resolutionId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToNewOrRegistered(Long id, String appealStatus) {
        appealRepository.moveToNewOrRegistered(id, appealStatus);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllByIdEmployee(Long id, Long startIndex, Long amount) {
        return appealRepository.findByIdEmployee(id, startIndex, amount);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void markMailIsSent(Long appealId) {
        appealRepository.markAsSent(appealId);
    }
}
