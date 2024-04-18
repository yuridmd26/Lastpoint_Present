package br.com.present.commons.util;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class PresentStreamUtils {

	public static <T>Stream<T> collectionToStream(Collection<T> collection){
		return Optional.ofNullable(collection).stream().flatMap(Collection::stream);
	}
	
}