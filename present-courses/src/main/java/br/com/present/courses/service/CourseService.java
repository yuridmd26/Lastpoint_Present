package br.com.present.courses.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.present.commons.exception.NoSuchRecordException;
import br.com.present.commons.service.BaseService;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.commons.model.mapper.TokenModelType;
import br.com.present.courses.business.CourseBusiness;
import br.com.present.courses.exception.keys.CourseExceptionKeys;
import br.com.present.commons.model.CourseEntity;
import br.com.present.courses.model.CourseRequestDTO;
import br.com.present.courses.model.CourseResponseDTO;
import br.com.present.courses.repository.ICourseRepository;

@Service
public class CourseService extends BaseService<ICourseRepository, CourseEntity, Long, CourseBusiness>{
	
	public CourseService(ICourseRepository repository, CourseBusiness business) {
		super(repository, business);
	}

	public CourseResponseDTO findById(Long id) {
		Optional<CourseEntity> courseEntity = getRepository().findById(id);
		
		if(courseEntity.isEmpty()) {
			throw new NoSuchRecordException(CourseExceptionKeys.NO_SUCH_RECORD_COURSE);
		}
		return PresentModelConverterUtils.map(courseEntity, CourseResponseDTO.class);
	}
	
	public CourseResponseDTO create(CourseRequestDTO courseDto) {
		Optional<List<CourseEntity>> lExistingCourseEntity = getRepository().findByCode(courseDto.getCode());

		lExistingCourseEntity.ifPresent(courseEntities -> getBusiness().validCourseCodeAlreadyExist(courseEntities));
		
		CourseEntity courseEntity = PresentModelConverterUtils.map(courseDto, CourseEntity.class);
		CourseEntity courseResp = getRepository().save(courseEntity);
		
		return PresentModelConverterUtils.map(courseResp, CourseResponseDTO.class);
	}
	
	public CourseResponseDTO update(Long id, CourseRequestDTO courseDto){
		Optional<CourseEntity> existingCourseEntity = getRepository().findById(id);
		
		if (existingCourseEntity.isPresent()) {
			Optional<List<CourseEntity>> lExistingCourseEntity = getRepository().findByCode(courseDto.getCode());
			lExistingCourseEntity.ifPresent(courseEntities -> getBusiness()
					.validCourseCodeAlreadyExistWhenOtherRecord(id, courseEntities));

			CourseEntity courseEntity = existingCourseEntity.get();
			PresentModelConverterUtils.map(courseDto, courseEntity);
			getRepository().save(courseEntity);

			return PresentModelConverterUtils.map(courseEntity, CourseResponseDTO.class);
        }
        throw new NoSuchRecordException(CourseExceptionKeys.NO_SUCH_RECORD_COURSE);
	}
	
	public void delete(Long id) {
		Optional<CourseEntity> existingCourse = getRepository().findById(id);
		
		if (existingCourse.isEmpty()) {
			throw new NoSuchRecordException(CourseExceptionKeys.NO_SUCH_RECORD_COURSE);
		}
		getRepository().deleteById(id);
	}
	
	public List<CourseResponseDTO> findAll() {
		List<CourseEntity> lCourseEntity = getRepository().findAll();
		
		if(lCourseEntity.isEmpty()) {			
			return Collections.emptyList();
		}
		return PresentModelConverterUtils.map(lCourseEntity, new TokenModelType<List<CourseResponseDTO>>(){}.getType());
	}
}