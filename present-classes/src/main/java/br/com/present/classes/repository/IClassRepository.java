package br.com.present.classes.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.present.classes.model.ClassesNowAndFutureResponseDTO;
import br.com.present.commons.model.ClassEntity;
import br.com.present.commons.repository.IBaseRepository;

@Repository
public interface IClassRepository extends IBaseRepository<ClassEntity, Long> {

    @Query("SELECT cla FROM ClassEntity cla WHERE cla.groupEntity.id = :groupId" +
            " AND cla.disciplineEntity.id = :disciplineId AND cla.eventEntity.id = :eventId")
    Optional<List<ClassEntity>> findByGroIdAndDiscIdAndEveId(@Param("groupId") Long groupId,
                                                             @Param("disciplineId") Long disciplineId,
                                                             @Param("eventId") Long eventId);
    
    @Query("SELECT new br.com.present.classes.model.ClassesNowAndFutureResponseDTO(eve.id as id,"
            + " eve.name as name, eve.description as description, eve.startDateTime as startDatetime, eve.endDateTime as endDatetime,"
            + " eve.userEntity.name as teacherName, dis.name disciplineName, gro.name groupName) "
            + " FROM UserEventEntity uev "
            + " INNER JOIN uev.eventEntity eve "
            + " INNER JOIN eve.classEntity cla "
            + " INNER JOIN cla.groupEntity gro "
            + " INNER JOIN cla.disciplineEntity dis "
            + " WHERE uev.userEntity.id = :userId AND"
            + " ((eve.startDateTime <= :dateNow AND eve.endDateTime >= :dateNow) OR"
            + " (eve.startDateTime > :dateNow AND eve.startDateTime <= :weekAfter))"
            + " ORDER BY eve.startDateTime ASC, eve.endDateTime ASC")
    Optional<List<ClassesNowAndFutureResponseDTO>> findClassesOfStudentByUserId(@Param("userId") Long userId, @Param("dateNow") OffsetDateTime  dateNow,
                                                                                @Param("weekAfter") OffsetDateTime  weekAfter);
    
    @Query("SELECT new br.com.present.classes.model.ClassesNowAndFutureResponseDTO(eve.id as id,"
            + " eve.name as name, eve.description as description, eve.startDateTime as startDatetime, eve.endDateTime as endDatetime,"
            + " eve.userEntity.name as teacherName, dis.name disciplineName, gro.name groupName) "
            + " FROM ClassEntity cla "
            + " INNER JOIN cla.eventEntity eve "
            + " INNER JOIN cla.disciplineEntity dis "
            + " INNER JOIN cla.groupEntity gro "
            + " WHERE eve.userEntity.id = :userId AND"
            + " (eve.startDateTime <= :dateNow AND eve.endDateTime >= :dateNow) OR"
            + " (eve.startDateTime > :dateNow AND eve.startDateTime <= :weekAfter)"
            + " ORDER BY eve.startDateTime ASC, eve.endDateTime ASC")
    Optional<List<ClassesNowAndFutureResponseDTO>> findClassesOfProfByUserId(@Param("userId") Long userId, @Param("dateNow") OffsetDateTime  dateNow,
                                                                             @Param("weekAfter") OffsetDateTime  weekAfter);

}