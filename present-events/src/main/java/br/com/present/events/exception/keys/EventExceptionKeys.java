package br.com.present.events.exception.keys;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class EventExceptionKeys {
	
	public static final String NO_SUCH_RECORD_EVENT = "eventService.err.noSuchRecordEvent";
	public static final String NO_SUCH_RECORD_USER_EVENT = "eventService.err.noSuchRecordUserEvent";
	public static final String RECORD_ALREADY_EXIST_EVENT = "eventService.err.recordAlreadyExistEvent";
	public static final String START_NOT_GTE_END_DATE_EVENT = "eventService.err.startNotGteEndDateEvent";
	public static final String RECORD_DATE_CONFLICT_EVENT = "eventService.err.recordDateConflictEvent";
	public static final String RECORD_ALREADY_EXIST_USER_EVENT = "userEventService.err.recordAlreadyExistUserEvent";
	public static final String RECORD_DATE_CONFLICT_USER_EVENT = "userEventService.err.recordDateConflictUserEvent";

}