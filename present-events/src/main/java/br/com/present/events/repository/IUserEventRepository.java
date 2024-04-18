package br.com.present.events.repository;

import br.com.present.commons.model.UserEventEntity;
import br.com.present.commons.repository.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserEventRepository extends IBaseRepository<UserEventEntity, Long> {

    @Query("SELECT ue FROM UserEventEntity ue WHERE ue.userEntity.id = :userId" +
            " AND ue.eventEntity.id = :eventId")
    Optional<List<UserEventEntity>> findByUserIdAndEventId(@Param("userId") Long userId, @Param("eventId") Long eventId);

    @Query("SELECT uev FROM UserEventEntity uev " +
            "INNER JOIN uev.eventEntity eve " +
            "INNER JOIN uev.userEntity use " +
            "WHERE ((eve.startDateTime <= :startDateTime AND eve.endDateTime > :startDateTime) " +
            "OR (eve.startDateTime < :endDateTime AND eve.endDateTime >= :endDateTime)) " +
            "AND eve.id != :eventId AND use.id = :userId")
    Optional<List<UserEventEntity>> findBetweenDates(@Param("startDateTime") OffsetDateTime startDateTime,
                                                 @Param("endDateTime") OffsetDateTime endDateTime,
                                                 @Param("eventId") Long eventId,
                                                 @Param("userId") Long userId);
}