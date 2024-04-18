package br.com.present.groups.business;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.present.groups.exception.ApiGroupCodeAlreadyExistException;
import br.com.present.groups.exception.keys.GroupExceptionKeys;
import br.com.present.commons.model.GroupEntity;

@Component
public final class GroupBusiness {
	
	public void validGroupCodeAlreadyExist(List<GroupEntity> lExistingGroupEntity) {
		if (!lExistingGroupEntity.isEmpty()) {
			throw new ApiGroupCodeAlreadyExistException(GroupExceptionKeys.RECORD_ALREADY_EXIST_GROUP);
		}
	}
	
	public void validGroupCodeAlreadyExistWhenOtherRecord(Long idGroup, List<GroupEntity> lExistingGroupEntity) {
		List<GroupEntity> lGroupDifferentChanged = lExistingGroupEntity.stream()
				.filter(g -> !g.getId().equals(idGroup)).toList();

		validGroupCodeAlreadyExist(lGroupDifferentChanged);
	}
}