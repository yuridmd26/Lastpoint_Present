package br.com.present.commons.docs.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InfoDocs {

	private String title;
	private String description;
	private String version;
	private License license;
	private Contact contact;

}