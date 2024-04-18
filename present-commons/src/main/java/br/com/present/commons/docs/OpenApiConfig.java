package br.com.present.commons.docs;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.present.commons.util.PresentFileUtils;
import br.com.present.commons.util.PresentInternationalizationUtils;
import br.com.present.commons.util.consts.MicroservicesConsts;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig extends DocsApiConfig<OpenAPI> {

	@Bean
	@Override
	protected OpenAPI configure() {
		return new OpenAPI()
			.servers(getServers())
			.info(getInfo());
	}
	
	@Override
	protected String getTitle() {
		return PresentInternationalizationUtils.getMessage("docsConfig.title");
	}
	
	@Override
	protected String getDescription() {
		return PresentInternationalizationUtils.getMessage("docsConfig.description");
	}

	@Override
	protected String getVersion() {
		return PresentFileUtils.getVersionPom("pom.xml");
	}
	
	@Override
	protected List<Server> getServers() {
		return List.of(new Server().url(MicroservicesConsts.Gateway.REFERENCE)
				.description(IDocsMsgConsts.Server.DEFAULT_NAME));
	}
	
	private Info getInfo() {
		return new Info()
	        .title(getInfoDocs().getTitle())
	        .description(getInfoDocs().getDescription())
	        .version(getInfoDocs().getVersion())
	        .license(getLicense())
	        .contact(getContact());
	}
	
	private License getLicense() {
		return new License()
			.name(getInfoDocs().getLicense().getName())
			.url(getInfoDocs().getLicense().getUrl());
	}
	
	private Contact getContact() {
		return new Contact()
			.name(getInfoDocs().getContact().getName())
	        .url(getInfoDocs().getContact().getUrl())
	        .email(getInfoDocs().getContact().getEmail());
	}
	
}
