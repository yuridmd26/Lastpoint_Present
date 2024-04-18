package br.com.present.courses.business;

import java.util.List;

import br.com.present.commons.model.CourseEntity;
import org.springframework.stereotype.Component;

import br.com.present.courses.exception.ApiCourseCodeAlreadyExistException;
import br.com.present.courses.exception.keys.CourseExceptionKeys;

@Component
public final class CourseBusiness {
	public void validCourseCodeAlreadyExist(List<CourseEntity> lExistingCourseEntity) {
		if (!lExistingCourseEntity.isEmpty()) {
			throw new ApiCourseCodeAlreadyExistException(CourseExceptionKeys.RECORD_ALREADY_EXIST_COURSE);
		}
	}
	
	public void validCourseCodeAlreadyExistWhenOtherRecord(Long idCourse, List<CourseEntity> lExistingCourseEntity) {
		List<CourseEntity> lCourseDifferentChanged = lExistingCourseEntity.stream()
				.filter(c -> !c.getId().equals(idCourse)).toList();

		validCourseCodeAlreadyExist(lCourseDifferentChanged);
	}
}