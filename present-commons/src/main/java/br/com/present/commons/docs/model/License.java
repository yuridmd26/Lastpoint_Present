package br.com.present.commons.docs.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class License {

	private String name;
	private String url;

}