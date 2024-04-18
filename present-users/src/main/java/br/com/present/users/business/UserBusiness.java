package br.com.present.users.business;

import java.util.List;

import br.com.present.auths.config.constants.SecurityPasswordConsts;
import br.com.present.auths.config.exception.ApiLoginException;
import br.com.present.auths.config.exception.ApiPasswordException;
import br.com.present.users.model.UserRequestDTO;
import org.springframework.stereotype.Component;

import br.com.present.users.exception.ApiUserLoginExistException;
import br.com.present.users.exception.keys.UserExceptionKeys;
import br.com.present.commons.model.UserEntity;

@Component
public final class UserBusiness {
	
	public void validExistingUserEntity(List<UserEntity> lExistingUserEntity) {
		if (!lExistingUserEntity.isEmpty()) {
			throw new ApiUserLoginExistException(UserExceptionKeys.LOGIN_ALREADY_EXIST_USER);
		}
	}
	
	public void validUserSecurityData(UserRequestDTO userDto) {
		if(isInvalidSizeLogin(userDto)) {
			throw new ApiLoginException("userBusiness.err.invalidSizeLogin");
		}
		if(isInvalidSizePassword(userDto)) {
			throw new ApiPasswordException("userBusiness.err.invalidSizePassword");
		}

		checkPasswordStrengthWithoutHash(userDto.getPassword());
	}

	private boolean isInvalidSizeLogin(UserRequestDTO userDto) {
		return userDto.getLogin().length() < SecurityPasswordConsts.MIN_LENGTH_LOGIN ||
				userDto.getLogin().length() > SecurityPasswordConsts.MAX_LENGTH_LOGIN;
	}
	
	private boolean isInvalidSizePassword(UserRequestDTO userDto) {
		return userDto.getPassword().length() < SecurityPasswordConsts.MIN_LENGTH_PASSWORD ||
				userDto.getPassword().length() > SecurityPasswordConsts.MAX_LENGTH_PASSWORD;
	}

	private void checkPasswordStrengthWithoutHash(String password) {
		Long countNumerics = password.chars().filter(c -> c >= '0' && c <= '9').count();
		Long countUppercase = password.chars().filter(c -> c >= 'A' && c <= 'Z').count();
		Long countLowercase = password.chars().filter(c -> c >= 'a' && c <= 'z').count();
		Long countSymbols = password.length() - (countNumerics + countUppercase + countLowercase);

		checkMinimumQuantityDefaultPassword(countNumerics, countUppercase, countLowercase, countSymbols);
	}

	private void checkMinimumQuantityDefaultPassword(Long countNumerics, Long countUppercase, Long countLowercase, Long countSymbols) throws ApiPasswordException {
		if(countNumerics < SecurityPasswordConsts.COUNT_MIN_NUMERIC) {
			throw new ApiPasswordException("userBusiness.erro.weakPasswordNumeric");
		}
		if(countUppercase < SecurityPasswordConsts.COUNT_MIN_UPPERCASE) {
			throw new ApiPasswordException("userBusiness.erro.weakPasswordUppercase");
		}
		if(countLowercase < SecurityPasswordConsts.COUNT_MIN_LOWERCASE) {
			throw new ApiPasswordException("userBusiness.erro.weakPasswordLowercase");
		}
		if(countSymbols < SecurityPasswordConsts.COUNT_MIN_SYMBOLS) {
			throw new ApiPasswordException("userBusiness.erro.weakPasswordSymbols");
		}
	}
}