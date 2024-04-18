package br.com.present.commons.service;

import br.com.present.commons.repository.IBaseRepository;

public abstract class BaseService<R extends IBaseRepository<E, I>, E, I, B> {

	private final R repository;
	private final B business;
	
	protected BaseService(R repository, B business) {
		this.repository = repository;
		this.business = business;
	}
	
	protected R getRepository() {
		return this.repository;
	}
	
	protected B getBusiness() {
		return this.business;
	}

}