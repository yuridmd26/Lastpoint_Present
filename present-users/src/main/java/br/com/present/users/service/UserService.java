package br.com.present.users.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.present.users.model.UserTypeResponseDTO;
import org.springframework.stereotype.Service;

import br.com.present.commons.exception.NoSuchRecordException;
import br.com.present.commons.model.UserEntity;
import br.com.present.commons.model.mapper.TokenModelType;
import br.com.present.commons.service.BaseService;
import br.com.present.commons.util.PresentModelConverterUtils;
import br.com.present.users.business.UserBusiness;
import br.com.present.users.exception.keys.UserExceptionKeys;
import br.com.present.users.model.UserRequestDTO;
import br.com.present.users.model.UserResponseDTO;
import br.com.present.users.repository.IUserRepository;

@Service
public class UserService extends BaseService<IUserRepository, UserEntity, Long, UserBusiness> {

	public UserService(IUserRepository repository, UserBusiness business) {
		super(repository, business);
	}

	public UserResponseDTO findById(Long id) {
		Optional<UserEntity> userEntity = getRepository().findById(id);
		
		if(userEntity.isEmpty()) {
			throw new NoSuchRecordException(UserExceptionKeys.NO_SUCH_RECORD_USER);
		}
		return PresentModelConverterUtils.map(userEntity, UserResponseDTO.class);
	}
	
	public UserResponseDTO create(UserRequestDTO userDto) {
		Optional<List<UserEntity>> lExistingUserEntity = getRepository().findByLogin(userDto.getLogin());

        lExistingUserEntity.ifPresent(userEntities -> getBusiness().validExistingUserEntity(userEntities));

		UserEntity userEntity = PresentModelConverterUtils.map(userDto, UserEntity.class);
		UserEntity userResp = getRepository().save(userEntity);
		
		return PresentModelConverterUtils.map(userResp, UserResponseDTO.class);
	}

	public UserResponseDTO update(Long id, UserRequestDTO userDto){
		Optional<UserEntity> existingUserEntity = getRepository().findById(id);

		if (existingUserEntity.isPresent()) {
			UserEntity userEntity = existingUserEntity.get();
			PresentModelConverterUtils.map(userDto, userEntity);
			getRepository().save(userEntity);
			
            return PresentModelConverterUtils.map(userEntity, UserResponseDTO.class);
        }
        throw new NoSuchRecordException(UserExceptionKeys.NO_SUCH_RECORD_USER);
	}

	public void delete(Long id) {
		Optional<UserEntity> existingUser = getRepository().findById(id);
		
		if(existingUser.isEmpty()) {
			throw new NoSuchRecordException(UserExceptionKeys.NO_SUCH_RECORD_USER);
		}
		getRepository().deleteById(id);
	}
	
	public List<UserResponseDTO> findAll() {
		List<UserEntity> lUsersEntity = getRepository().findAll();
		
		if(lUsersEntity.isEmpty()) {			
			return Collections.emptyList();
		}
		return PresentModelConverterUtils.map(lUsersEntity, new TokenModelType<List<UserResponseDTO>>(){}.getType());
	}
	
	public UserTypeResponseDTO findTypeByUserId(Long id) {
		Optional<String> userType = getRepository().findTypeById(id);
		
		if(userType.isEmpty()) {
			throw new NoSuchRecordException(UserExceptionKeys.NO_SUCH_RECORD_USER);
		}
		
		return new UserTypeResponseDTO(userType.get());
	}
}
