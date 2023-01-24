package com.education.repository;

import com.education.entity.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Связь с БД с таблицей nomenclature
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long> {

    /**
     * Метод предоставляет не заархивированную номенклатуру по id
     */
    @Query(value = "SELECT n FROM Nomenclature n WHERE n.id = :id AND n.archivedDate is null")
    Optional<Nomenclature> findByIdNotArchived(@Param("id") Long id);

    /**
     * Метод предоставляет список не заархивированных номенклатур по id
     */
    @Query(value = "SELECT n FROM Nomenclature n WHERE n.id in :list AND n.archivedDate is null")
    List<Nomenclature> findAllByIdNotArchived(@Param("list") Iterable<Long> list);

    /**
     * Метод переводит в архив номенклатуру присваивая значение даты архивации
     */
    @Modifying
    @Query (value = "UPDATE edo.nomenclature SET archived_date = current_timestamp WHERE id = :id", nativeQuery = true)
    void moveToArchive(@Param("id") Long id);
}
