package br.com.present.events.service;

import br.com.present.commons.exception.NoSuchRecordException;
import br.com.present.commons.model.*;
import br.com.present.commons.model.mapper.TokenModelType;
import br.com.present.commons.service.BaseService;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.events.business.UserEventBusiness;
import br.com.present.events.consumer.IUserConsumer;
import br.com.present.events.consumer.model.UserResponseDTO;
import br.com.present.events.exception.keys.EventExceptionKeys;
import br.com.present.events.model.EventResponseDTO;
import br.com.present.events.model.UserEventRequestDTO;
import br.com.present.events.model.UserEventResponseDTO;
import br.com.present.events.model.mapper.UserEventEntityToRespDTOCustomMapper;
import br.com.present.events.model.mapper.UserEventReqDTOToEntityCustomMapper;
import br.com.present.events.repository.IUserEventRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserEventService extends BaseService<IUserEventRepository, UserEventEntity, Long, UserEventBusiness>{

	private final IUserConsumer userConsumer;
	private final EventService eventService;

	public UserEventService(
		IUserEventRepository repository,
		UserEventBusiness business,
		IUserConsumer userConsumer,
		EventService eventService
	) {
		super(repository, business);
		this.userConsumer = userConsumer;
		this.eventService = eventService;
	}

	public UserEventResponseDTO findById(Long id) {
		Optional<UserEventEntity> userEventEntity = getRepository().findById(id);
		
		if(userEventEntity.isEmpty()) {
			throw new NoSuchRecordException(EventExceptionKeys.NO_SUCH_RECORD_USER_EVENT);
		}
		return PresentModelConverterUtils.map(userEventEntity, UserEventResponseDTO.class,
				new UserEventEntityToRespDTOCustomMapper());
	}
	
	public UserEventResponseDTO create(UserEventRequestDTO userEventDto) {
		Optional<List<UserEventEntity>> lExistingUserEventEntity = getRepository().
				findByUserIdAndEventId(userEventDto.getUserId(), userEventDto.getEventId());

		lExistingUserEventEntity.ifPresent(userEventEntities -> getBusiness().validUserEventAlreadyDone(userEventEntities));

		UserResponseDTO userDto = userConsumer.findById(userEventDto.getUserId());
		EventResponseDTO eventDto = eventService.findById(userEventDto.getEventId());
		validRecordDateConflictUserEvent(userDto, eventDto);

		UserEventEntity userEventEntity = PresentModelConverterUtils.map(userEventDto, UserEventEntity.class,
				new UserEventReqDTOToEntityCustomMapper());

		UserEventEntity userEventResp = getRepository().save(userEventEntity);

		return PresentModelConverterUtils.map(userEventResp, UserEventResponseDTO.class,
				new UserEventEntityToRespDTOCustomMapper());
	}
	
	public UserEventResponseDTO update(Long id, UserEventRequestDTO userEventDTO){
		Optional<UserEventEntity> existingUserEventEntity = getRepository().findById(id);
		
		if (existingUserEventEntity.isPresent()) {
			Optional<List<UserEventEntity>> lExistingUserEventEntity = getRepository()
					.findByUserIdAndEventId(userEventDTO.getUserId(), userEventDTO.getEventId());

			lExistingUserEventEntity.ifPresent(userEventEntities -> getBusiness().
					validUserEventAlreadyDoneInAnotherRecord(id, userEventEntities));

			UserEventEntity userEventEntity = existingUserEventEntity.get();
			updateUserIntoUserEvent(userEventDTO, userEventEntity);
			updateEventIntoUserEvent(userEventDTO, userEventEntity);
			validRecordDateConflictUserEvent(userEventEntity);

			getRepository().save(userEventEntity);
			
			return PresentModelConverterUtils.map(userEventEntity, UserEventResponseDTO.class,
					new UserEventEntityToRespDTOCustomMapper());
        }
        throw new NoSuchRecordException(EventExceptionKeys.NO_SUCH_RECORD_USER_EVENT);
	}

	private void validRecordDateConflictUserEvent(UserResponseDTO userDto, EventResponseDTO eventDto) {
		Optional<List<UserEventEntity>> lUserEventBetweenDates = getRepository().findBetweenDates(
				eventDto.getStartDateTime(), eventDto.getEndDateTime(), eventDto.getId(), userDto.getId());

		lUserEventBetweenDates.ifPresent(userEventEntities -> getBusiness().validRecordDateConflictUserEvent(userEventEntities));
	}

	private void validRecordDateConflictUserEvent(UserEventEntity userEventEntity) {
		Optional<List<UserEventEntity>> lUserEventBetweenDates = getRepository().findBetweenDates(
				userEventEntity.getEventEntity().getStartDateTime(), userEventEntity.getEventEntity().getEndDateTime(),
					userEventEntity.getEventEntity().getId(), userEventEntity.getUserEntity().getId());

		lUserEventBetweenDates.ifPresent(userEventEntities -> getBusiness().validRecordDateConflictUserEvent(userEventEntities));
	}
	
	private void updateUserIntoUserEvent(UserEventRequestDTO userEventDTO, UserEventEntity userEventEntity) {
		if(!userEventEntity.getUserEntity().getId().equals(userEventDTO.getUserId())){
			UserResponseDTO newUser = userConsumer.findById(userEventDTO.getUserId());
			userEventEntity.setUserEntity(PresentModelConverterUtils.map(newUser, UserEntity.class));
		}
	}

	private void updateEventIntoUserEvent(UserEventRequestDTO userEventDTO, UserEventEntity userEventEntity) {
		if(!userEventEntity.getEventEntity().getId().equals(userEventDTO.getEventId())){
			EventResponseDTO newEvent = eventService.findById(userEventDTO.getEventId());
			userEventEntity.setEventEntity(PresentModelConverterUtils.map(newEvent, EventEntity.class));
		}
	}
	
	public void delete(Long id) {
		Optional<UserEventEntity> existingUserEventEntity = getRepository().findById(id);
		
		if (existingUserEventEntity.isEmpty()) {
			throw new NoSuchRecordException(EventExceptionKeys.NO_SUCH_RECORD_USER_EVENT);
		}
		getRepository().deleteById(id);
	}
	
	public List<UserEventResponseDTO> findAll() {
		List<UserEventEntity> lUserEventEntity = getRepository().findAll();
		
		if(lUserEventEntity.isEmpty()) {
			return Collections.emptyList();
		}
		return PresentModelConverterUtils.map(lUserEventEntity,
				new TokenModelType<List<UserEventResponseDTO>>(){}.getType(), new UserEventEntityToRespDTOCustomMapper());
	}
}