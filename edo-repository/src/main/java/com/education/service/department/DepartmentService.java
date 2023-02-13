package com.education.service.department;

import com.education.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department save(Department obj);
    void moveToArchive(Long id);
    Optional<Department> findById(Long id);
    List<Department> findAllById(List<Long> ids);
    Optional<Department> findByIdNotArchived(Long id);
    List<Department> findAllByIdNotArchived(List<Long> ids);

}
