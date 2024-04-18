package br.com.present.events.business;

import java.time.ZonedDateTime;
import java.util.List;

import br.com.present.commons.model.EventEntity;
import br.com.present.events.exception.ApiRecordDateConflictEventException;
import br.com.present.events.exception.ApiStartNotGteEndDateEventException;
import org.springframework.stereotype.Component;

import br.com.present.events.exception.ApiEventCodeAlreadyExistException;
import br.com.present.events.exception.keys.EventExceptionKeys;

@Component
public final class EventBusiness {

	public void validEventCodeAlreadyExist(List<EventEntity> lExistingEventEntity) {
		if (!lExistingEventEntity.isEmpty()) {
			throw new ApiEventCodeAlreadyExistException(EventExceptionKeys.RECORD_ALREADY_EXIST_EVENT);
		}
	}
	
	public void validEventCodeAlreadyExistWhenOtherRecord(Long idEvent, List<EventEntity> lExistingEventEntity) {
		List<EventEntity> lEventDifferentChanged = lExistingEventEntity.stream()
				.filter(e -> !e.getId().equals(idEvent)).toList();

		validEventCodeAlreadyExist(lEventDifferentChanged);
	}

	public void validRecordDateConflictEvent(List<EventEntity> lEventBetweenDates) {
		if (!lEventBetweenDates.isEmpty()) {
			throw new ApiRecordDateConflictEventException(EventExceptionKeys.RECORD_DATE_CONFLICT_EVENT);
		}
	}

	public void validStartDateGreaterThanOrEqualEndDate(ZonedDateTime startDate, ZonedDateTime endDate) {
		if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
			throw new ApiStartNotGteEndDateEventException(EventExceptionKeys.START_NOT_GTE_END_DATE_EVENT);
		}
	}
}