package br.com.present.classes.service;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.present.classes.exception.ApiClassTypeInvalidException;
import org.springframework.stereotype.Service;

import br.com.present.classes.business.ClassBusiness;
import br.com.present.classes.consumer.IDisciplineConsumer;
import br.com.present.classes.consumer.IEventConsumer;
import br.com.present.classes.consumer.IGroupConsumer;
import br.com.present.classes.consumer.IUserConsumer;
import br.com.present.classes.consumer.model.DisciplineResponseDTO;
import br.com.present.classes.consumer.model.EventResponseDTO;
import br.com.present.classes.consumer.model.GroupResponseDTO;
import br.com.present.classes.consumer.model.UserTypeResponseDTO;
import br.com.present.classes.exception.keys.ClasseExceptionKeys;
import br.com.present.classes.model.ClassRequestDTO;
import br.com.present.classes.model.ClassResponseDTO;
import br.com.present.classes.model.ClassesNowAndFutureResponseDTO;
import br.com.present.classes.model.mapper.ClassEntityToRespDTOCustomMapper;
import br.com.present.classes.model.mapper.ClassReqDTOToEntityCustomMapper;
import br.com.present.classes.repository.IClassRepository;
import br.com.present.classes.type.UserType;
import br.com.present.commons.exception.NoSuchRecordException;
import br.com.present.commons.model.ClassEntity;
import br.com.present.commons.model.DisciplineEntity;
import br.com.present.commons.model.EventEntity;
import br.com.present.commons.model.GroupEntity;
import br.com.present.commons.model.mapper.TokenModelType;
import br.com.present.commons.service.BaseService;
import br.com.present.commons.util.PresentModelConverterUtils;

@Service
public class ClassService extends BaseService<IClassRepository, ClassEntity, Long, ClassBusiness>{

	private final IGroupConsumer groupConsumer;
	private final IDisciplineConsumer disciplineConsumer;
	private final IEventConsumer eventConsumer;
	private final IUserConsumer userConsumer;

	public ClassService(
		IClassRepository repository,
		ClassBusiness business,
		IGroupConsumer groupConsumer,
		IDisciplineConsumer disciplineConsumer,
		IEventConsumer eventConsumer,
		IUserConsumer userConsumer
	) {
		super(repository, business);
		this.groupConsumer = groupConsumer;
		this.disciplineConsumer = disciplineConsumer;
		this.eventConsumer = eventConsumer;
		this.userConsumer = userConsumer;
	}

	public ClassResponseDTO findById(Long id) {
		Optional<ClassEntity> classEntity = getRepository().findById(id);
		
		if(classEntity.isEmpty()) {
			throw new NoSuchRecordException(ClasseExceptionKeys.NO_SUCH_RECORD_CLASS);
		}
		return PresentModelConverterUtils.map(classEntity, ClassResponseDTO.class, new ClassEntityToRespDTOCustomMapper());
	}
	
	public ClassResponseDTO create(ClassRequestDTO classDto) {
		Optional<List<ClassEntity>> lExistingClassEntity = getRepository().
				findByGroIdAndDiscIdAndEveId(classDto.getGroupId(), classDto.getDisciplineId(), classDto.getEventId());

		lExistingClassEntity.ifPresent(classEntities -> getBusiness().validClassAlreadyDone(classEntities));

		groupConsumer.findById(classDto.getGroupId());
		disciplineConsumer.findById(classDto.getDisciplineId());
		eventConsumer.findById(classDto.getEventId());

		ClassEntity classEntity = PresentModelConverterUtils.map(classDto, ClassEntity.class,
				new ClassReqDTOToEntityCustomMapper());
		ClassEntity classResp = getRepository().save(classEntity);

		return PresentModelConverterUtils.map(classResp, ClassResponseDTO.class, new ClassEntityToRespDTOCustomMapper());
	}
	
	public ClassResponseDTO update(Long id, ClassRequestDTO classDto){
		Optional<ClassEntity> existingClassEntity = getRepository().findById(id);
		
		if (existingClassEntity.isPresent()) {
			Optional<List<ClassEntity>> lExistingClassEntity = getRepository().
					findByGroIdAndDiscIdAndEveId(classDto.getGroupId(), classDto.getDisciplineId(), classDto.getEventId());

			lExistingClassEntity.ifPresent(classEntities -> getBusiness().validClassAlreadyDoneInAnotherRecord(id, classEntities));

			ClassEntity classEntity = existingClassEntity.get();
			updateGroupIntoClass(classDto, classEntity);
			updateDisciplineIntoClass(classDto, classEntity);
			updateEventIntoClass(classDto, classEntity);

			getRepository().save(classEntity);
			
			return PresentModelConverterUtils.map(classEntity, ClassResponseDTO.class, new ClassEntityToRespDTOCustomMapper());
        }
        throw new NoSuchRecordException(ClasseExceptionKeys.NO_SUCH_RECORD_CLASS);
	}

	private void updateGroupIntoClass(ClassRequestDTO classDto, ClassEntity classEntity) {
		if(!classEntity.getGroupEntity().getId().equals(classDto.getGroupId())){
			GroupResponseDTO newGroup = groupConsumer.findById(classDto.getGroupId());
			classEntity.setGroupEntity(PresentModelConverterUtils.map(newGroup, GroupEntity.class));
		}
	}

	private void updateDisciplineIntoClass(ClassRequestDTO classDto, ClassEntity classEntity) {
		if(!classEntity.getDisciplineEntity().getId().equals(classDto.getDisciplineId())){
			DisciplineResponseDTO newDiscipline = disciplineConsumer.findById(classDto.getDisciplineId());
			classEntity.setDisciplineEntity(PresentModelConverterUtils.map(newDiscipline, DisciplineEntity.class));
		}
	}

	private void updateEventIntoClass(ClassRequestDTO classDto, ClassEntity classEntity) {
		if(!classEntity.getEventEntity().getId().equals(classDto.getEventId())){
			EventResponseDTO newEvent = eventConsumer.findById(classDto.getEventId());
			classEntity.setEventEntity(PresentModelConverterUtils.map(newEvent, EventEntity.class));
		}
	}
	
	public void delete(Long id) {
		Optional<ClassEntity> existingClassEntity = getRepository().findById(id);
		
		if (existingClassEntity.isEmpty()) {
			throw new NoSuchRecordException(ClasseExceptionKeys.NO_SUCH_RECORD_CLASS);
		}
		getRepository().deleteById(id);
	}
	
	public List<ClassResponseDTO> findAll() {
		List<ClassEntity> lClassEntity = getRepository().findAll();
		
		if(lClassEntity.isEmpty()) {			
			return Collections.emptyList();
		}
		return PresentModelConverterUtils.map(lClassEntity,
				new TokenModelType<List<ClassResponseDTO>>(){}.getType(), new ClassEntityToRespDTOCustomMapper());
	}
	
	public List<ClassesNowAndFutureResponseDTO> findClassesUserForTypeByUserId(Long userId) {
        UserTypeResponseDTO userType = userConsumer.findTypeByUserId(userId);

		UserType type;
		try {
			type = UserType.valueOf(userType.getType());
		} catch (IllegalArgumentException e) {
			throw new ApiClassTypeInvalidException(ClasseExceptionKeys.TYPE_INVALID);
		}
        return switch (type) {
            case PROF -> findClassesOfProfByUserId(userId);
            case STUDENT -> findClassesOfStudentByUserId(userId);
        };
    }

    private List<ClassesNowAndFutureResponseDTO> findClassesOfProfByUserId(Long userId) {
        OffsetDateTime dateNow = OffsetDateTime.now();
        OffsetDateTime weekAfter = dateNow.plusDays(7);
        Optional<List<ClassesNowAndFutureResponseDTO>> lUserClassEntity = getRepository().findClassesOfProfByUserId(userId, dateNow, weekAfter);

        if(lUserClassEntity.isEmpty()) {
            return Collections.emptyList();
        }

        getBusiness().updateStatusClass(dateNow, lUserClassEntity.get());

        return PresentModelConverterUtils.map(lUserClassEntity, new TokenModelType<List<ClassesNowAndFutureResponseDTO>>(){}.getType(),
				new ClassEntityToRespDTOCustomMapper());
    }
	private List<ClassesNowAndFutureResponseDTO> findClassesOfStudentByUserId(Long userId) {
        OffsetDateTime dateNow = OffsetDateTime.now();
        OffsetDateTime weekAfter = dateNow.plusDays(7);
        Optional<List<ClassesNowAndFutureResponseDTO>> lUserClassEntity = getRepository().findClassesOfStudentByUserId(userId, dateNow, weekAfter);

        if(lUserClassEntity.isEmpty()) {
            return Collections.emptyList();
        }

		getBusiness().updateStatusClass(dateNow, lUserClassEntity.get());

        return PresentModelConverterUtils.map(lUserClassEntity, new TokenModelType<List<ClassesNowAndFutureResponseDTO>>(){}.getType(),
				new ClassEntityToRespDTOCustomMapper());
    }
}