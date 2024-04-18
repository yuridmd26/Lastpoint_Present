package br.com.present.attendances.business;

import br.com.present.attendances.exception.ApiAttendanceAlreadyExistException;
import br.com.present.attendances.exception.keys.AttendanceExceptionKeys;
import br.com.present.commons.model.AttendanceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class AttendanceBusiness {

    public void validAttendanceAlreadyDone(List <AttendanceEntity> lExistingAttendanceEntity) {
        if (!lExistingAttendanceEntity.isEmpty()) {
            throw new ApiAttendanceAlreadyExistException(AttendanceExceptionKeys.RECORD_ALREADY_EXIST_ATTENDANCE);
        }
    }

    public void validAttendanceAlreadyDoneInAnotherRecord(Long idAttendance, List<AttendanceEntity> lExistingAttendanceEntity) {
        List<AttendanceEntity> lAttendanceDifferentChanged = lExistingAttendanceEntity.stream()
                .filter(g -> !g.getId().equals(idAttendance)).toList();

        validAttendanceAlreadyDone(lAttendanceDifferentChanged);
    }
}
