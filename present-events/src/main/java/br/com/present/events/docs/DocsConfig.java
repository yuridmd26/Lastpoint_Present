package br.com.present.events.docs;

import br.com.present.commons.util.PresentFileUtils;
import org.springframework.context.annotation.Configuration;

import br.com.present.commons.docs.OpenApiConfig;

@Configuration
public class DocsConfig extends OpenApiConfig {

    @Override
    protected String getVersion() {
        return PresentFileUtils.getVersionPom("present-events/pom.xml");
    }
}