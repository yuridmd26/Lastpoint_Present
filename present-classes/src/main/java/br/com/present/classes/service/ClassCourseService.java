package br.com.present.classes.service;

import br.com.present.classes.business.ClassCourseBusiness;
import br.com.present.classes.consumer.ICouseConsumer;
import br.com.present.classes.consumer.model.CourseResponseDTO;
import br.com.present.classes.exception.keys.ClasseExceptionKeys;
import br.com.present.classes.model.ClassCourseRequestDTO;
import br.com.present.classes.model.ClassCourseResponseDTO;
import br.com.present.classes.model.ClassResponseDTO;
import br.com.present.classes.model.mapper.ClassCourseEntityToRespDTOCustomMapper;
import br.com.present.classes.model.mapper.ClassCourseReqDTOToEntityCustomMapper;
import br.com.present.classes.repository.IClassCourseRepository;
import br.com.present.commons.exception.NoSuchRecordException;
import br.com.present.commons.model.*;
import br.com.present.commons.model.mapper.TokenModelType;
import br.com.present.commons.service.BaseService;
import br.com.present.commons.util.PresentModelConverterUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClassCourseService extends BaseService<IClassCourseRepository, ClassCourseEntity, Long, ClassCourseBusiness>{

	private final ICouseConsumer couseConsumer;
	private final ClassService classService;

	public ClassCourseService(
		IClassCourseRepository repository,
		ClassCourseBusiness business,
		ICouseConsumer couseConsumer,
		ClassService classService
	) {
		super(repository, business);
		this.couseConsumer = couseConsumer;
		this.classService = classService;
	}

	public ClassCourseResponseDTO findById(Long id) {
		Optional<ClassCourseEntity> classCourseEntity = getRepository().findById(id);
		
		if(classCourseEntity.isEmpty()) {
			throw new NoSuchRecordException(ClasseExceptionKeys.NO_SUCH_RECORD_CLASS_COURSE);
		}
		return PresentModelConverterUtils.map(classCourseEntity, ClassCourseResponseDTO.class,
				new ClassCourseEntityToRespDTOCustomMapper());
	}
	
	public ClassCourseResponseDTO create(ClassCourseRequestDTO classCourseDto) {
		Optional<List<ClassCourseEntity>> lExistingClassCourseEntity = getRepository().
				findByClassIdAndCourseId(classCourseDto.getClassId(), classCourseDto.getCourseId());

		lExistingClassCourseEntity.ifPresent(classCourseEntities -> getBusiness().validClassCourseAlreadyDone(classCourseEntities));

		couseConsumer.findById(classCourseDto.getCourseId());
		classService.findById(classCourseDto.getClassId());

		ClassCourseEntity classCourseEntity = PresentModelConverterUtils.map(classCourseDto, ClassCourseEntity.class,
				new ClassCourseReqDTOToEntityCustomMapper());
		ClassCourseEntity classCourseResp = getRepository().save(classCourseEntity);

		return PresentModelConverterUtils.map(classCourseResp, ClassCourseResponseDTO.class,
				new ClassCourseEntityToRespDTOCustomMapper());
	}
	
	public ClassCourseResponseDTO update(Long id, ClassCourseRequestDTO classCourseDTO){
		Optional<ClassCourseEntity> existingClassCourseEntity = getRepository().findById(id);
		
		if (existingClassCourseEntity.isPresent()) {
			Optional<List<ClassCourseEntity>> lExistingClassCourseEntity = getRepository()
					.findByClassIdAndCourseId(classCourseDTO.getClassId(), classCourseDTO.getCourseId());

			lExistingClassCourseEntity.ifPresent(classCourseEntities -> getBusiness().
					validClassCourseAlreadyDoneInAnotherRecord(id, classCourseEntities));

			ClassCourseEntity classCourseEntity = existingClassCourseEntity.get();
			updateClassIntoClassCourse(classCourseDTO, classCourseEntity);
			updateCourseIntoClassCourse(classCourseDTO, classCourseEntity);

			getRepository().save(classCourseEntity);
			
			return PresentModelConverterUtils.map(classCourseEntity, ClassCourseResponseDTO.class,
					new ClassCourseEntityToRespDTOCustomMapper());
        }
        throw new NoSuchRecordException(ClasseExceptionKeys.NO_SUCH_RECORD_CLASS_COURSE);
	}

	private void updateClassIntoClassCourse(ClassCourseRequestDTO classCourseDTO, ClassCourseEntity classCourseEntity) {
		if(!classCourseEntity.getClassEntity().getId().equals(classCourseDTO.getClassId())){
			ClassResponseDTO newClass = classService.findById(classCourseDTO.getClassId());
			classCourseEntity.setClassEntity(PresentModelConverterUtils.map(newClass, ClassEntity.class));
		}
	}

	private void updateCourseIntoClassCourse(ClassCourseRequestDTO classCourseDTO, ClassCourseEntity classCourseEntity) {
		if(!classCourseEntity.getCourseEntity().getId().equals(classCourseDTO.getCourseId())){
			CourseResponseDTO newClassCourse = couseConsumer.findById(classCourseDTO.getCourseId());
			classCourseEntity.setCourseEntity(PresentModelConverterUtils.map(newClassCourse, CourseEntity.class));
		}
	}
	
	public void delete(Long id) {
		Optional<ClassCourseEntity> existingClassCourseEntity = getRepository().findById(id);
		
		if (existingClassCourseEntity.isEmpty()) {
			throw new NoSuchRecordException(ClasseExceptionKeys.NO_SUCH_RECORD_CLASS_COURSE);
		}
		getRepository().deleteById(id);
	}
	
	public List<ClassCourseResponseDTO> findAll() {
		List<ClassCourseEntity> lClassCourseEntity = getRepository().findAll();
		
		if(lClassCourseEntity.isEmpty()) {
			return Collections.emptyList();
		}
		return PresentModelConverterUtils.map(lClassCourseEntity,
				new TokenModelType<List<ClassCourseResponseDTO>>(){}.getType(), new ClassCourseEntityToRespDTOCustomMapper());
	}
}