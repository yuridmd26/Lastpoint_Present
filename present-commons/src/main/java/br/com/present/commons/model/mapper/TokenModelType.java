package br.com.present.commons.model.mapper;

import org.modelmapper.TypeToken;

public class TokenModelType<T> extends TypeToken<T> implements ITokenModelType<T> {}