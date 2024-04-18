package br.com.present.commons.util;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import br.com.present.commons.util.bean.MessageSourceSingleton;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class PresentInternationalizationUtils {
	
	private static final MessageSourceSingleton messageSource = MessageSourceSingleton.getInstance();
	
	public static String getMessage(String nameArquivo) {
		try {
			return messageSource.getMessage(nameArquivo, new Object[] {}, LocaleContextHolder.getLocale());
		} catch (Exception e) {
			return nameArquivo;
		}
	}
	
	public static String getMessage(String nameArquivo, Object[] args) {
		try {
			return messageSource.getMessage(nameArquivo, args, LocaleContextHolder.getLocale());
		} catch (Exception e) {
			return nameArquivo;
		}
	}
	
	public static String getMessage(String nameArquivo, Object[] args, Locale locale) {
		try {
			return messageSource.getMessage(nameArquivo, args, locale);
		} catch (Exception e) {
			return nameArquivo;
		}
	}
	
}