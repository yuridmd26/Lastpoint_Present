package br.com.present.commons.util;

import com.google.gson.Gson;

import br.com.present.commons.util.bean.JsonConverterSingleton;
import lombok.experimental.UtilityClass;

import java.nio.ByteBuffer;

@UtilityClass
public final class PresentJsonUtils {

	private static final Gson jsonConverter = JsonConverterSingleton.getConverter();
	
	public static <T> T fromJson(String json, Class<T> classOfT) {
		return jsonConverter.fromJson(json, classOfT);
	}
	
	public static String toJson(Object obj) {
		return jsonConverter.toJson(obj);
	}

	public static <T> T fromJson(ByteBuffer obj, Class<T> classOfT) {
		byte[] byteArray = new byte[obj.remaining()];
		obj.get(byteArray);

		String responseBody = new String(byteArray);
		return jsonConverter.fromJson(responseBody, classOfT);
	}

	public static String toJson(ByteBuffer obj) {
		byte[] byteArray = new byte[obj.remaining()];
		obj.get(byteArray);

		return new String(byteArray);
	}
}