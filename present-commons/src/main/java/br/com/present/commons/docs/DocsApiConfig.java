package br.com.present.commons.docs;

import java.util.List;

import br.com.present.commons.docs.model.Contact;
import br.com.present.commons.docs.model.InfoDocs;
import br.com.present.commons.docs.model.License;
import io.swagger.v3.oas.models.servers.Server;

public abstract class DocsApiConfig<T> {

	private InfoDocs infoDocs;
	
	protected abstract T configure();
	protected abstract String getTitle();
	protected abstract String getDescription();
	protected abstract String getVersion();
	protected abstract List<Server> getServers();
	
	protected InfoDocs getInfoDocs() {
		if(infoDocs == null) {
			infoDocs = buildInfoDocs();
		}
		return infoDocs;	
	}

	private InfoDocs buildInfoDocs() {
		return InfoDocs
			.builder()
				.title(getTitle())
				.description(getDescription())
				.version(getVersion())
				.license(buildLicense())
				.contact(buildContact())
			.build();
	}
	
	private License buildLicense() {
		return License
			.builder()
				.name(IDocsMsgConsts.Licence.NAME)
				.url(IDocsMsgConsts.Licence.URL)
			.build();
	}
	
	protected Contact buildContact() {
		return Contact
			.builder()
				.name(IDocsMsgConsts.Contact.NAME)
				.url(IDocsMsgConsts.Contact.SITE)
				.email(IDocsMsgConsts.Contact.EMAIL)
			.build();
	}
}
