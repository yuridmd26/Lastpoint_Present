package br.com.present.commons.model;

import java.io.Serial;
import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = -2011925122329475423L;

	public abstract Long getId();
	public abstract void setId(Long id);

}