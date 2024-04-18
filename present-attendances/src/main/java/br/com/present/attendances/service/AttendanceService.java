package br.com.present.attendances.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.present.attendances.consumer.IEventConsumer;
import br.com.present.attendances.consumer.IUserConsumer;
import br.com.present.attendances.consumer.model.EventResponseDTO;
import br.com.present.attendances.consumer.model.UserResponseDTO;
import br.com.present.attendances.model.AttendanceStudentsDTO;
import br.com.present.attendances.model.mapper.AttendReqDTOToEntityCustomMapper;
import br.com.present.attendances.model.mapper.AttendEntityToRespDTOCustomMapper;
import br.com.present.commons.model.EventEntity;
import br.com.present.commons.model.UserEntity;
import org.springframework.stereotype.Service;

import br.com.present.attendances.business.AttendanceBusiness;
import br.com.present.attendances.exception.keys.AttendanceExceptionKeys;
import br.com.present.commons.model.AttendanceEntity;
import br.com.present.attendances.model.AttendanceRequestDTO;
import br.com.present.attendances.model.AttendanceResponseDTO;
import br.com.present.attendances.repository.IAttendanceRepository;
import br.com.present.commons.exception.NoSuchRecordException;
import br.com.present.commons.service.BaseService;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.commons.model.mapper.TokenModelType;

@Service
public class AttendanceService extends BaseService<IAttendanceRepository, AttendanceEntity, Long, AttendanceBusiness> {

	private final IUserConsumer userConsumer;
	private final IEventConsumer eventConsumer;

	public AttendanceService(
			IAttendanceRepository repository,
			AttendanceBusiness business,
			IUserConsumer userConsumer,
			IEventConsumer eventConsumer
	) {
		super(repository, business);
		this.userConsumer = userConsumer;
		this.eventConsumer = eventConsumer;
	}

	public AttendanceResponseDTO findById(Long id) {
		Optional<AttendanceEntity> attendanceEntity = getRepository().findById(id);

		if (attendanceEntity.isEmpty()) {
			throw new NoSuchRecordException(AttendanceExceptionKeys.NO_SUCH_RECORD_ATTENDANCE);
		}
		return PresentModelConverterUtils.map(attendanceEntity.get(), AttendanceResponseDTO.class,
				new AttendEntityToRespDTOCustomMapper());
	}

	public AttendanceResponseDTO create(AttendanceRequestDTO attendanceDto) {
		Optional<List<AttendanceEntity>> lExistingAttendanceEntity = getRepository().
				findByUserIdAndEventId(attendanceDto.getUserId(), attendanceDto.getEventId());

		lExistingAttendanceEntity.ifPresent(attendanceEntities -> getBusiness().validAttendanceAlreadyDone(attendanceEntities));

		userConsumer.findById(attendanceDto.getUserId());
		eventConsumer.findById(attendanceDto.getEventId());

		AttendanceEntity attendanceEntity = PresentModelConverterUtils.map(attendanceDto,
				AttendanceEntity.class, new AttendReqDTOToEntityCustomMapper());
		AttendanceEntity attendanceResp = getRepository().save(attendanceEntity);

		return PresentModelConverterUtils.map(attendanceResp,
				AttendanceResponseDTO.class, new AttendEntityToRespDTOCustomMapper());
	}

	public AttendanceResponseDTO update(Long id, AttendanceRequestDTO attendanceDto) {
		Optional<AttendanceEntity> existingAttendanceEntity = getRepository().findById(id);

		if (existingAttendanceEntity.isPresent()) {
			Optional<List<AttendanceEntity>> lExistingAttendanceEntity = getRepository().
					findByUserIdAndEventId(attendanceDto.getUserId(), attendanceDto.getEventId());
			lExistingAttendanceEntity.ifPresent(attendanceEntities -> getBusiness().
					validAttendanceAlreadyDoneInAnotherRecord(id, attendanceEntities));

			AttendanceEntity attendanceEntity = existingAttendanceEntity.get();
			updateUserIntoAttendance(attendanceDto, attendanceEntity);
			updateEventIntoAttendance(attendanceDto, attendanceEntity);

			getRepository().save(attendanceEntity);

			return PresentModelConverterUtils.map(attendanceEntity, AttendanceResponseDTO.class,
					new AttendEntityToRespDTOCustomMapper());
		}
		throw new NoSuchRecordException(AttendanceExceptionKeys.NO_SUCH_RECORD_ATTENDANCE);
	}

	private void updateEventIntoAttendance(AttendanceRequestDTO attendanceDto, AttendanceEntity attendanceEntity) {
		if (!attendanceEntity.getEventEntity().getId().equals(attendanceDto.getEventId())) {
			EventResponseDTO newEvent = eventConsumer.findById(attendanceDto.getEventId());
			attendanceEntity.setEventEntity(PresentModelConverterUtils.map(newEvent, EventEntity.class));
		}
	}

	private void updateUserIntoAttendance(AttendanceRequestDTO attendanceDto, AttendanceEntity attendanceEntity) {
		if (!attendanceEntity.getUserEntity().getId().equals(attendanceDto.getUserId())) {
			UserResponseDTO newUser = userConsumer.findById(attendanceDto.getUserId());
			attendanceEntity.setUserEntity(PresentModelConverterUtils.map(newUser, UserEntity.class));
		}
	}

	public void delete(Long id) {
		Optional<AttendanceEntity> attendanceEntity = getRepository().findById(id);

		if (attendanceEntity.isEmpty()) {
			throw new NoSuchRecordException(AttendanceExceptionKeys.NO_SUCH_RECORD_ATTENDANCE);
		}
		getRepository().deleteById(id);
	}

	public List<AttendanceResponseDTO> findAll() {
		List<AttendanceEntity> lAttendanceEntity = getRepository().findAll();

		if (lAttendanceEntity.isEmpty()) {
			return Collections.emptyList();
		}
		return PresentModelConverterUtils.map(lAttendanceEntity,
				new TokenModelType<List<AttendanceResponseDTO>>() {
				}.getType(), new AttendEntityToRespDTOCustomMapper());
	}

	public List<AttendanceStudentsDTO> findAttendanceStudents(Long id) {
		Optional<List<AttendanceStudentsDTO>> lAttendanceStudents = getRepository().findAttendanceStudentsByEventId(id);

		if (lAttendanceStudents.isEmpty()) {
			throw new NoSuchRecordException(AttendanceExceptionKeys.NO_SUCH_ATTENDANCE_STUDENTS);
		}

		return lAttendanceStudents.get();
	}
}