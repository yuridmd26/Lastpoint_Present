package br.com.present.auths.service;

public interface IEncoderService {

	public String encrypt(String senha);
	public Boolean compareHashes(String umPassword, String outroPassword);
	public Boolean checkStrongPassword(String hashSenha);
	
}