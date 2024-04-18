package br.com.present.commons.util;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.collect.Sets;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class PresentCollectionUtils {

	@SafeVarargs
	public static <E extends @Nullable Object> Set<E> newHashSet(E... elements) {
		return Sets.newHashSet(elements);
	}
	
	public Collector<CharSequence, ?, String> joining(String lineSeparator) {		
		return Collectors.joining(lineSeparator);
	}
	
}