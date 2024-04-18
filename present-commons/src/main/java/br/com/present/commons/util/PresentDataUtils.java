package br.com.present.commons.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class PresentDataUtils {

	public static ZonedDateTime getZonedDateTimeNow() {
		return ZonedDateTime.now(ZoneId.of("Z"));
	}
	
}