package com.education.service.approvalsheet;

import com.education.model.dto.ApprovalSheetDto;
import com.education.service.BaseInterface;

import java.util.List;

/**
 * @author Ivan Chursinov
 */

public interface ApprovalSheetService extends BaseInterface<ApprovalSheetDto> {

    ApprovalSheetDto save(ApprovalSheetDto approvalSheet);

    void moveToArchive(Long id);

    ApprovalSheetDto findById(Long id);

    List<ApprovalSheetDto> findAllById(List<Long> ids);

    ApprovalSheetDto findByIdNotArchived(Long id);

    List<ApprovalSheetDto> findAllByIdNotArchived(List<Long> ids);

}
