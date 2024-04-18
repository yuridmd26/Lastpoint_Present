package br.com.present.auths.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.present.auths.service.IEncoderService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EncoderServiceImpl implements IEncoderService {
	
	private final PasswordEncoder encoder;

	@Override
	public String encrypt(String senha) {
		return encoder.encode(senha);
	}

	@Override
	public Boolean compareHashes(String umPassword, String outroPassword) {
		return encoder.matches(umPassword, outroPassword);
	}

	@Override
	public Boolean checkStrongPassword(String hashSenha) {
		return encoder.upgradeEncoding(hashSenha);
	}
	
}