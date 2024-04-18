package br.com.present.events.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.present.commons.model.UserEntity;
import br.com.present.events.consumer.IUserConsumer;
import br.com.present.events.consumer.model.UserResponseDTO;
import br.com.present.events.model.mapper.EventEntityToRespDTOCustomMapper;
import br.com.present.events.model.mapper.EventReqDTOToEntityCustomMapper;
import org.springframework.stereotype.Service;

import br.com.present.commons.exception.NoSuchRecordException;
import br.com.present.commons.service.BaseService;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.commons.model.mapper.TokenModelType;
import br.com.present.events.business.EventBusiness;
import br.com.present.events.exception.keys.EventExceptionKeys;
import br.com.present.commons.model.EventEntity;
import br.com.present.events.model.EventRequestDTO;
import br.com.present.events.model.EventResponseDTO;
import br.com.present.events.repository.IEventRepository;

@Service
public class EventService extends BaseService<IEventRepository, EventEntity, Long, EventBusiness> {

	private final IUserConsumer userConsumer;

	public EventService(
		IEventRepository repository,
		EventBusiness business,
		IUserConsumer userConsumer
	) {
		super(repository, business);
		this.userConsumer = userConsumer;
	}

	public EventResponseDTO findById(Long id) {
		Optional<EventEntity> eventEntity = getRepository().findById(id);
		
		if(eventEntity.isEmpty()) {
			throw new NoSuchRecordException(EventExceptionKeys.NO_SUCH_RECORD_EVENT);
		}
		return PresentModelConverterUtils.map(eventEntity.get(), EventResponseDTO.class,
				new EventEntityToRespDTOCustomMapper());
	}
	
	public EventResponseDTO create(EventRequestDTO eventDto) {
		getBusiness().validStartDateGreaterThanOrEqualEndDate(eventDto.getStartDateTime(), eventDto.getEndDateTime());

		Optional<List<EventEntity>> lExistingEventEntity = getRepository().findByCode(eventDto.getCode());
		lExistingEventEntity.ifPresent(eventEntities -> getBusiness().validEventCodeAlreadyExist(eventEntities));

		userConsumer.findById(eventDto.getUserId());
		EventEntity eventEntity = PresentModelConverterUtils.map(eventDto, EventEntity.class,
				new EventReqDTOToEntityCustomMapper());

		validRecordDateConflictEvent(eventEntity);

		EventEntity eventResp = getRepository().save(eventEntity);

		return PresentModelConverterUtils.map(eventResp, EventResponseDTO.class,
				new EventEntityToRespDTOCustomMapper());
	}

	public EventResponseDTO update(Long id, EventRequestDTO eventDto){
		getBusiness().validStartDateGreaterThanOrEqualEndDate(eventDto.getStartDateTime(), eventDto.getEndDateTime());
		Optional<EventEntity> existingEvent = getRepository().findById(id);

		if (existingEvent.isPresent()) {
			Optional<List<EventEntity>> lExistingEventEntity = getRepository().findByCode(eventDto.getCode());

			lExistingEventEntity.ifPresent(eventEntities -> getBusiness().
					validEventCodeAlreadyExistWhenOtherRecord(id, eventEntities));

			EventEntity eventEntity = existingEvent.get();
			updateUserIntoEvent(eventDto, eventEntity);

			PresentModelConverterUtils.map(eventDto, eventEntity, new EventReqDTOToEntityCustomMapper());
			validRecordDateConflictEvent(eventEntity);

			getRepository().save(eventEntity);
			
            return PresentModelConverterUtils.map(eventEntity, EventResponseDTO.class,
					new EventEntityToRespDTOCustomMapper());
        }
        throw new NoSuchRecordException(EventExceptionKeys.NO_SUCH_RECORD_EVENT);
	}

	private void validRecordDateConflictEvent(EventEntity eventEntity) {
		Optional<List<EventEntity>> lEventBetweenDates = getRepository().
				findBetweenDates(eventEntity.getStartDateTime(), eventEntity.getEndDateTime(),
						eventEntity.getId() != null ? eventEntity.getId() : 0, eventEntity.getUserEntity().getId());

		lEventBetweenDates.ifPresent(eventEntities -> getBusiness().validRecordDateConflictEvent(eventEntities));
	}

	private void updateUserIntoEvent(EventRequestDTO eventDto, EventEntity eventEntity) {
		if(!eventEntity.getUserEntity().getId().equals(eventDto.getUserId())){
			UserResponseDTO newUser = userConsumer.findById(eventDto.getUserId());
			eventEntity.setUserEntity(PresentModelConverterUtils.map(newUser, UserEntity.class));
		}
	}
	
	public void delete(Long id) {
		Optional<EventEntity> eventEntity = getRepository().findById(id);
		
		if (eventEntity.isEmpty()) {
			throw new NoSuchRecordException(EventExceptionKeys.NO_SUCH_RECORD_EVENT);
		}
		getRepository().deleteById(id);
	}
	
	public List<EventResponseDTO> findAll() {
		List<EventEntity> lEventEntity = getRepository().findAll();
		
		if(lEventEntity.isEmpty()) {
			return Collections.emptyList();
		}
		return PresentModelConverterUtils.map(lEventEntity,
				new TokenModelType<List<EventResponseDTO>>(){}.getType(), new EventEntityToRespDTOCustomMapper());
	}

}