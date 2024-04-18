package br.com.present.commons.util.bean;

import br.com.present.commons.util.adapter.ZonedDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.ZonedDateTime;

public class JsonConverterSingleton {

	private static Gson converter;

	private JsonConverterSingleton() {}
	
	public static Gson getConverter() {
		if (converter == null) {
			converter = new GsonBuilder()
					.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
					.create();
        }
        return converter;
	}

}