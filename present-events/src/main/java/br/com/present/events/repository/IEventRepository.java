package br.com.present.events.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.present.commons.repository.IBaseRepository;
import br.com.present.commons.model.EventEntity;

@Repository
public interface IEventRepository  extends IBaseRepository<EventEntity, Long> {

	Optional<List<EventEntity>> findByCode(String code);

	@Query("SELECT e FROM EventEntity e WHERE ((e.startDateTime <= :startDateTime AND e.endDateTime > :startDateTime) " +
			"OR (e.startDateTime < :endDateTime AND e.endDateTime >= :endDateTime)) " +
			"AND e.id != :eventId AND e.userEntity.id = :userId")
	Optional<List<EventEntity>> findBetweenDates(@Param("startDateTime") OffsetDateTime startDateTime,
												 @Param("endDateTime") OffsetDateTime endDateTime,
												 @Param("eventId") Long eventId,
												 @Param("userId") Long userId);

}