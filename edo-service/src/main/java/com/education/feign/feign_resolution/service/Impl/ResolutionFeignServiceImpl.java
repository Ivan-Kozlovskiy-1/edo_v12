package com.education.feign.feign_resolution.service.Impl;

import com.education.feign.feign_resolution.service.ResolutionFeignService;
import com.education.feign.feign_resolution.service.ResolutionService;
import com.education.model.dto.ResolutionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolutionFeignServiceImpl implements ResolutionService {
    private final ResolutionFeignService resolutionFeignService;

    @Override
    public ResolutionDto save(ResolutionDto resolution) {
        return resolutionFeignService.save(resolution);
    }

    @Override
    public void moveToArchive(Long id) {
        resolutionFeignService.moveToArchive(id);
    }

    @Override
    public ResolutionDto findById(Long id) {
        return resolutionFeignService.findById(id);
    }

    @Override
    public List<ResolutionDto> findAllById(Iterable<Long> ids) {
        return resolutionFeignService.findAllById(ids);
    }

    @Override
    public ResolutionDto findByIdNotArchived(Long id) {
        return resolutionFeignService.findByIdNotArchived(id);
    }

    @Override
    public List<ResolutionDto> findAllByIdNotArchived(Iterable<Long> ids) {
        return resolutionFeignService.findAllByIdNotArchived(ids);
    }
}