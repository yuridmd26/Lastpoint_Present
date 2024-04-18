package br.com.present.events.business;

import br.com.present.commons.model.UserEventEntity;
import br.com.present.events.exception.ApiRecordDateConflictUserEventException;
import br.com.present.events.exception.ApiUserEventAlreadyExistException;
import br.com.present.events.exception.keys.EventExceptionKeys;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class UserEventBusiness {

    public void validUserEventAlreadyDone(List<UserEventEntity> lExistingUserEventEntity) {
        if (!lExistingUserEventEntity.isEmpty()) {
            throw new ApiUserEventAlreadyExistException(EventExceptionKeys.RECORD_ALREADY_EXIST_USER_EVENT);
        }
    }

    public void validUserEventAlreadyDoneInAnotherRecord(Long idUserEvent, List<UserEventEntity> lExistingUserEventEntity) {
        List<UserEventEntity> lUserEventDifferentChanged = lExistingUserEventEntity.stream()
                .filter(ue -> !ue.getId().equals(idUserEvent)).toList();

        validUserEventAlreadyDone(lUserEventDifferentChanged);
    }

    public void validRecordDateConflictUserEvent(List<UserEventEntity> lUserEventBetweenDates) {
        if (!lUserEventBetweenDates.isEmpty()) {
            throw new ApiRecordDateConflictUserEventException(EventExceptionKeys.RECORD_DATE_CONFLICT_USER_EVENT);
        }
    }
}