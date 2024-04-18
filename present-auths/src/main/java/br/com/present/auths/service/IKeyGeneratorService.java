package br.com.present.auths.service;

import com.auth0.jwt.algorithms.Algorithm;

import br.com.present.auths.config.exception.ApiKeyGenerateException;

public interface IKeyGeneratorService {

	public Algorithm getAlgorithm();
	public void generateKey() throws ApiKeyGenerateException;
	
}