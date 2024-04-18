package br.com.present.auths.config;

import java.util.List;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.present.commons.util.consts.MicroservicesConsts;

@Configuration
public abstract class CorsConfig {
	
	private final List<String> lPathsReleased = List.of(
		MicroservicesConsts.Gateway.REFERENCE
    );
	
	@Bean
	public WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry
					.addMapping(getMappingReleased())
					.allowedMethods(getAllowedMethods())
					.allowedOrigins(getPathsReleased().toArray(new String[0]));
			}
		};
	}
	
	protected String getMappingReleased() {
		return "/**";
	}
	
	protected String[] getAllowedMethods() {
		return new String[] {
			HttpMethod.GET.name(),
			HttpMethod.PATCH.name(),
			HttpMethod.POST.name(),
			HttpMethod.PUT.name(),
			HttpMethod.DELETE.name()
		};
	}
	
	protected List<String> getPathsReleased() {
		return this.lPathsReleased;
	}

}