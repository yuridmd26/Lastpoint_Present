package br.com.present.classes.business;

import br.com.present.classes.exception.ApiClassAlreadyExistException;
import br.com.present.classes.exception.keys.ClasseExceptionKeys;
import br.com.present.classes.model.ClassesNowAndFutureResponseDTO;
import br.com.present.classes.type.ClassStatusType;
import br.com.present.commons.model.ClassEntity;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public final class ClassBusiness {

    public void validClassAlreadyDone(List<ClassEntity> lExistingClassEntity) {
        if (!lExistingClassEntity.isEmpty()) {
            throw new ApiClassAlreadyExistException(ClasseExceptionKeys.RECORD_ALREADY_EXIST_CLASS);
        }
    }

    public void validClassAlreadyDoneInAnotherRecord(Long idClass, List<ClassEntity> lExistingClassEntity) {
        List<ClassEntity> lClassDifferentChanged = lExistingClassEntity.stream()
                .filter(c -> !c.getId().equals(idClass)).toList();

        validClassAlreadyDone(lClassDifferentChanged);
    }

    public void updateStatusClass(OffsetDateTime dateNow, List<ClassesNowAndFutureResponseDTO> lUserClassEntity) {
        lUserClassEntity.stream().filter(c -> c.getStartDatetime().isBefore(dateNow)).
                forEach(c -> c.setStatus(ClassStatusType.ON_GOING));

        lUserClassEntity.stream().filter(c -> !c.getStartDatetime().isBefore(dateNow)).
                forEach(c -> c.setStatus(ClassStatusType.FUTURE));
    }
}