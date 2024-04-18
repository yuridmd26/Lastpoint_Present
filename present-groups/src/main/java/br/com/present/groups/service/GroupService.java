package br.com.present.groups.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.present.commons.exception.NoSuchRecordException;
import br.com.present.commons.service.BaseService;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.commons.model.mapper.TokenModelType;
import br.com.present.groups.business.GroupBusiness;
import br.com.present.groups.exception.keys.GroupExceptionKeys;
import br.com.present.commons.model.GroupEntity;
import br.com.present.groups.model.GroupRequestDTO;
import br.com.present.groups.model.GroupResponseDTO;
import br.com.present.groups.repository.IGroupRepository;

@Service
public class GroupService extends BaseService<IGroupRepository, GroupEntity, Long, GroupBusiness> {
	
	public GroupService(IGroupRepository repository, GroupBusiness business) {
		super(repository, business);
	}
	
	public GroupResponseDTO findById(Long id) {
		Optional<GroupEntity> groupEntity = getRepository().findById(id);
		
		if(groupEntity.isEmpty()) {
			throw new NoSuchRecordException(GroupExceptionKeys.NO_SUCH_RECORD_GROUP);
		}
		return PresentModelConverterUtils.map(groupEntity, GroupResponseDTO.class);
	}
	
	public GroupResponseDTO create(GroupRequestDTO groupDto) {
		Optional<List<GroupEntity>> lExistingGroupEntity = getRepository().findByCode(groupDto.getCode());

        lExistingGroupEntity.ifPresent(groupEntities -> getBusiness().validGroupCodeAlreadyExist(groupEntities));

		GroupEntity groupEntity = PresentModelConverterUtils.map(groupDto, GroupEntity.class);
		GroupEntity groupResp = getRepository().save(groupEntity);
		
		return PresentModelConverterUtils.map(groupResp, GroupResponseDTO.class);
	}
	
	public GroupResponseDTO update(Long id, GroupRequestDTO groupDTO) {
		Optional<GroupEntity> existingGroup = getRepository().findById(id);

		if (existingGroup.isPresent()) {
			Optional<List<GroupEntity>> lExistingGroupEntity = getRepository().findByCode(groupDTO.getCode());
			lExistingGroupEntity.ifPresent(groupEntities -> getBusiness()
					.validGroupCodeAlreadyExistWhenOtherRecord(id, groupEntities));

			GroupEntity groupEntity = existingGroup.get();
			PresentModelConverterUtils.map(groupDTO, groupEntity);
			getRepository().save(groupEntity);

			return PresentModelConverterUtils.map(groupEntity, GroupResponseDTO.class);
		}
		throw new NoSuchRecordException(GroupExceptionKeys.NO_SUCH_RECORD_GROUP);
	}
	
	public void delete(Long id) {
		Optional<GroupEntity> existingGroup = getRepository().findById(id);
		
		if (existingGroup.isEmpty()) {
			throw new NoSuchRecordException(GroupExceptionKeys.NO_SUCH_RECORD_GROUP);
		}
		getRepository().deleteById(id);
	}
	
	public List<GroupResponseDTO> findAll() {
		List<GroupEntity> lGroupsEntity = getRepository().findAll();
		
		if(lGroupsEntity.isEmpty()) {			
			return Collections.emptyList();
		}
		return PresentModelConverterUtils.map(lGroupsEntity, new TokenModelType<List<GroupResponseDTO>>(){}.getType());
	}
}