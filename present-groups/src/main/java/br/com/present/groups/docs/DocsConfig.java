package br.com.present.groups.docs;

import br.com.present.commons.util.PresentFileUtils;
import org.springframework.context.annotation.Configuration;

import br.com.present.commons.docs.OpenApiConfig;

@Configuration
public class DocsConfig extends OpenApiConfig {

    @Override
    protected String getVersion() {
        return PresentFileUtils.getVersionPom("present-groups/pom.xml");
    }
}