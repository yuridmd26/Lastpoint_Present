package br.com.present.disciplines.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.present.commons.exception.NoSuchRecordException;
import br.com.present.commons.service.BaseService;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.commons.model.mapper.TokenModelType;
import br.com.present.disciplines.business.DisciplineBusiness;
import br.com.present.disciplines.exception.keys.DisciplineExceptionKeys;
import br.com.present.commons.model.DisciplineEntity;
import br.com.present.disciplines.model.DisciplineRequestDTO;
import br.com.present.disciplines.model.DisciplineResponseDTO;
import br.com.present.disciplines.repository.IDisciplineRepository;

@Service
public class DisciplineService extends BaseService<IDisciplineRepository, DisciplineEntity, Long, DisciplineBusiness> {
	
	public DisciplineService(IDisciplineRepository repository, DisciplineBusiness business) {
		super(repository, business);
	}

	public DisciplineResponseDTO findById(Long id) {
		Optional<DisciplineEntity> disciplineEntity = getRepository().findById(id);
		
		if(disciplineEntity.isEmpty()) {
			throw new NoSuchRecordException(DisciplineExceptionKeys.NO_SUCH_RECORD_DISCIPLINE);
		}
		return PresentModelConverterUtils.map(disciplineEntity, DisciplineResponseDTO.class);
	}
	
	public DisciplineResponseDTO create(DisciplineRequestDTO disciplineDto) {
		Optional<List<DisciplineEntity>> lExistingDisciplineEntity = getRepository().findByCode(disciplineDto.getCode());

		lExistingDisciplineEntity.ifPresent(disciplineEntities -> getBusiness().
				validDisciplineCodeAlreadyExist(disciplineEntities));
		
		DisciplineEntity disciplineEntity = PresentModelConverterUtils.map(disciplineDto, DisciplineEntity.class);
		DisciplineEntity disciplineResp = getRepository().save(disciplineEntity);
		
		return PresentModelConverterUtils.map(disciplineResp, DisciplineResponseDTO.class);
	}
	
	public DisciplineResponseDTO update(Long id, DisciplineRequestDTO disciplineDto){
		Optional<DisciplineEntity> existingDisciplineEntity = getRepository().findById(id);

		if (existingDisciplineEntity.isPresent()) {
			Optional<List<DisciplineEntity>> lExistingDisciplineEntity = getRepository().findByCode(disciplineDto.getCode());
			lExistingDisciplineEntity.ifPresent(disciplineEntities -> getBusiness().
					validDisciplineCodeAlreadyExistWhenOtherRecord(id, disciplineEntities));

			DisciplineEntity disciplineEntity = existingDisciplineEntity.get();
			PresentModelConverterUtils.map(disciplineDto, disciplineEntity);
			getRepository().save(disciplineEntity);
			
            return PresentModelConverterUtils.map(disciplineEntity, DisciplineResponseDTO.class);
        }
        throw new NoSuchRecordException(DisciplineExceptionKeys.NO_SUCH_RECORD_DISCIPLINE);
	}
	
	public void delete(Long id) {
		Optional<DisciplineEntity> existingDiscipline = getRepository().findById(id);
		
		if (existingDiscipline.isEmpty()) {
			throw new NoSuchRecordException(DisciplineExceptionKeys.NO_SUCH_RECORD_DISCIPLINE);
		}
		getRepository().deleteById(id);
	}
	
	public List<DisciplineResponseDTO> findAll() {
		List<DisciplineEntity> lDisciplineEntity = getRepository().findAll();
		
		if(lDisciplineEntity.isEmpty()) {			
			return Collections.emptyList();
		}
		return PresentModelConverterUtils.map(lDisciplineEntity, new TokenModelType<List<DisciplineResponseDTO>>(){}.getType());
	}
}