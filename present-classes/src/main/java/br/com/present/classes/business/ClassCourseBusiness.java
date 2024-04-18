package br.com.present.classes.business;

import br.com.present.classes.exception.ApiClassCourseAlreadyExistException;
import br.com.present.classes.exception.keys.ClasseExceptionKeys;
import br.com.present.commons.model.ClassCourseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class ClassCourseBusiness {

    public void validClassCourseAlreadyDone(List<ClassCourseEntity> lExistingClassCourseEntity) {
        if (!lExistingClassCourseEntity.isEmpty()) {
            throw new ApiClassCourseAlreadyExistException(ClasseExceptionKeys.RECORD_ALREADY_EXIST_CLASS_COURSE);
        }
    }

    public void validClassCourseAlreadyDoneInAnotherRecord(Long idClassCourse, List<ClassCourseEntity> lExistingClassCourseEntity) {
        List<ClassCourseEntity> lClassCourseDifferentChanged = lExistingClassCourseEntity.stream()
                .filter(c -> !c.getId().equals(idClassCourse)).toList();

        validClassCourseAlreadyDone(lClassCourseDifferentChanged);
    }

}