package br.com.present.commons.docs.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Contact {

	private String name;
	private String url;
	private String email;

}