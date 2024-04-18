package br.com.present.commons.model.mapper;

import java.lang.reflect.Type;

public interface ITokenModelType<T> {

	Class<T> getRawType();
	Type getType();
	
}