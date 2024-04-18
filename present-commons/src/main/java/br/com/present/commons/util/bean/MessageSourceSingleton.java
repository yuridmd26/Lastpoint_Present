package br.com.present.commons.util.bean;

import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSourceSingleton extends ResourceBundleMessageSource {
	
	private static MessageSourceSingleton instance;
	
	private MessageSourceSingleton() {}
	
	public static MessageSourceSingleton getInstance() {
        if (instance == null) {
            instance = new MessageSourceSingleton();
            setDefaultConfig(instance);
        }
        return instance;
    }
	
	private static void setDefaultConfig(MessageSourceSingleton instance) {
		instance.setBasenames("messages/messages");
	}
	
}