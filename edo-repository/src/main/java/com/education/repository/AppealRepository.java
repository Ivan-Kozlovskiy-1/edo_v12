package com.education.repository;

import com.education.entity.Appeal;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Связь таблицы Appeal с базой данных
 */
@Repository
public interface AppealRepository extends JpaRepository<Appeal, Long> {

    @EntityGraph(attributePaths = {"creator", "signer"})
    Optional<Appeal> findById(Long id);

    @EntityGraph(attributePaths = {"creator", "signer"})
    List<Appeal> findAllById(Iterable<Long> ids);

    @Modifying
    @Query(value = "UPDATE appeal SET archived_date = current_timestamp where id = :id",
            nativeQuery = true)
    void moveToArchive(@Param("id") Long id);

    @Query("select r from Appeal r where r.id = :id and r.archivedDate is null")
    @EntityGraph(attributePaths = {"creator", "signer"})
    Optional<Appeal> findByIdNotArchived(@Param("id") Long id);

    @Query(value = "select r from Appeal r where r.id in :ids and r.archivedDate is null ")
    @EntityGraph(attributePaths = {"creator", "signer"})
    List<Appeal> findAllByIdNotArchived(@Param("ids") Iterable<Long> ids);

    @Query(value = "select a from Appeal a join a.question q where q.id = :questionId")
    @EntityGraph(attributePaths = {"creator", "signer"})
    Appeal findAppealByQuestionId(@Param("questionId") Long questionId);

    @Query(nativeQuery = true,
            value = "SELECT a.* FROM appeal a " +
                    "LEFT JOIN employee e ON a.creator_id = e.id " +
                    "WHERE e.id = :id ORDER BY a.id OFFSET :startIndex LIMIT :amount")
    List<Appeal> findByIdEmployee(@Param(value = "id") Long id,
                                  @Param(value = "startIndex") Long startIndex,
                                  @Param(value = "amount") Long amount);

    @Query(nativeQuery = true,
            value = "SELECT a.* FROM appeal a " +
                    "LEFT JOIN appeal_question aq on a.id = aq.appeal_id " +
                    "LEFT JOIN resolution r on aq.question_id = r.question_id " +
                    "WHERE r.id = :resolutionId")
    Appeal findAppealByResolutionId(@Param("resolutionId") Long resolutionId);


    @Modifying
    @Query(value = "UPDATE appeal SET appeal_status = 'UNDER_CONSIDERATION'" +
            " where id = (select a.id from appeal a" +
            " left join appeal_question aq on a.id = aq.appeal_id" +
            " left join resolution r on aq.question_id= r.question_id" +
            " where r.id = :resolutionId)", nativeQuery = true)
    void moveToUnderConsideration(@Param("resolutionId") Long resolutionId);

    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE appeal SET appeal_status = :appealStatus where id = :id")
    void moveToNewOrRegistered(@Param("id") Long id,
                               @Param("appealStatus") String appealStatus);

    @Modifying
    @Query(value = "UPDATE Appeal a SET a.isMailSent = true WHERE a.id = :appealId")
    void markAsSent(@Param("appealId") Long appealId);
}

