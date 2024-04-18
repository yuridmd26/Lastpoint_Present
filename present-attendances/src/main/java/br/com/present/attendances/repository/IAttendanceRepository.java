package br.com.present.attendances.repository;

import br.com.present.attendances.model.AttendanceStudentsDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.present.commons.model.AttendanceEntity;
import br.com.present.commons.repository.IBaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAttendanceRepository extends IBaseRepository<AttendanceEntity, Long> {

    @Query("SELECT att FROM AttendanceEntity att WHERE att.userEntity.id = :userId AND att.eventEntity.id = :eventId")
    Optional<List<AttendanceEntity>> findByUserIdAndEventId(@Param("userId") Long userId, @Param("eventId") Long eventId);

    @Query("SELECT new br.com.present.attendances.model.AttendanceStudentsDTO(use.code AS studentCode," +
            "  use.name AS studentName," +
            "  CASE WHEN att.id IS NOT NULL THEN TRUE ELSE FALSE END AS status" +
            ") " +
            "FROM UserEventEntity uev" +
            " INNER JOIN uev.eventEntity eve" +
            " INNER JOIN uev.userEntity use" +
            " LEFT JOIN eve.attendancesEntity att ON att.userEntity.id = use.id" +
            " WHERE uev.eventEntity.id = :eventId")
    Optional<List<AttendanceStudentsDTO>> findAttendanceStudentsByEventId(@Param("eventId") Long eventId);
}