package com.education.service.facsimile.impl;

import com.education.feign.feign_facsimile.FacsimileFeignClient;
import com.education.model.dto.FacsimileDto;
import com.education.model.dto.FilePoolDto;
import com.education.service.AbstractService;
import com.education.service.facsimile.FacsimileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Никита Бадеев
 * Service для сохранения факсимиле
 */
@Service
@Log4j2
public class FacsimileServiceImpl extends AbstractService<FacsimileFeignClient, FacsimileDto> implements FacsimileService {
    private final FacsimileFeignClient feignClient;

    public FacsimileServiceImpl(FacsimileFeignClient facsimileFeignClient, FacsimileFeignClient feignClient) {
        super(facsimileFeignClient);
        this.feignClient = feignClient;
    }

    /**
     * Method for saving Facsimile in file-storage
     *
     * @param multipartFile - file for save
     * @return Facsimile as FilePoolDto
     */
    @Override
    public FilePoolDto saveFacsimile(MultipartFile multipartFile) {
        return feignClient.saveFacsimile(multipartFile);
    }

    /**
     * Method for saving facsimile as Entity in DB
     *
     * @param jsonFile employee and others
     * @return facsimileDTO
     */
    @Override
    public FacsimileDto saveFacsimileEntity(String jsonFile) {
        return feignClient.saveFacsimileEntity(jsonFile);
    }

    /**
     * Method for archiving/unarchivig facsimile
     *
     * @param jsonFile data with id Facsimile and boolean isArchived
     * @return FacsimileDto
     */
    @Override
    public FacsimileDto archiveFacsimile(String jsonFile) {
        return feignClient.archiveFacsimile(jsonFile);
    }
}
