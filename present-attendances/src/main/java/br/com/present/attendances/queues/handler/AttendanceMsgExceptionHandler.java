package br.com.present.attendances.queues.handler;

import br.com.present.present.messenger.exception.handler.MessengerExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class AttendanceMsgExceptionHandler extends MessengerExceptionHandler {}
