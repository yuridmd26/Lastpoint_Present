package br.com.present.disciplines.business;

import java.util.List;

import br.com.present.commons.model.DisciplineEntity;
import org.springframework.stereotype.Component;

import br.com.present.disciplines.exception.ApiDisciplineCodeAlreadyExistException;
import br.com.present.disciplines.exception.keys.DisciplineExceptionKeys;

@Component
public final class DisciplineBusiness {

	public void validDisciplineCodeAlreadyExist(List<DisciplineEntity> lExistingDisciplineEntity) {
		if (!lExistingDisciplineEntity.isEmpty()) {
			throw new ApiDisciplineCodeAlreadyExistException(DisciplineExceptionKeys.RECORD_ALREADY_EXIST_DISCIPLINE);
		}
	}
	
	public void validDisciplineCodeAlreadyExistWhenOtherRecord(Long idGroup, List<DisciplineEntity> lExistingDisciplineEntity) {
		List<DisciplineEntity> lDisciplineDifferentChanged = lExistingDisciplineEntity.stream()
				.filter(d -> !d.getId().equals(idGroup)).toList();

		validDisciplineCodeAlreadyExist(lDisciplineDifferentChanged);
	}
}
