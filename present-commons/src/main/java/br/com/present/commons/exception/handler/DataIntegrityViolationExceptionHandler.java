package br.com.present.commons.exception.handler;

import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.commons.exception.type.DataIntegrityViolationType;
import br.com.present.commons.util.PresentDataUtils;
import br.com.present.commons.util.PresentInternationalizationUtils;
import lombok.experimental.UtilityClass;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class DataIntegrityViolationExceptionHandler {

    public static ResponseEntity<ApiExceptionResponseDTO> handlerDataIntegrityViolationException(DataIntegrityViolationException exception){
        String foreignKey = extractForeignKey(exception.getMessage() != null ? exception.getMessage() : "");
        String messageIntegrityViolation = handlerMessageIntegrityViolation(foreignKey);
        ApiExceptionResponseDTO apiException;

        if(messageIntegrityViolation != null){
            apiException = new ApiExceptionResponseDTO(
                    new String[]{PresentInternationalizationUtils.getMessage("apiExceptionHandler.err.dataIntegrityViolation",
                            new String[]{messageIntegrityViolation})},
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    PresentDataUtils.getZonedDateTimeNow()
            );
        }else{
            apiException = new ApiExceptionResponseDTO(
                    new String[]{exception.getMessage()},
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    PresentDataUtils.getZonedDateTimeNow()
            );
        }
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    private static String extractForeignKey(String text) {
        String regex = "FOREIGN KEY \\(`([^`]+)`\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String handlerMessageIntegrityViolation(String foreignKey) {
        try {
            DataIntegrityViolationType foreignKeyType = DataIntegrityViolationType.valueOf(foreignKey);
            return PresentInternationalizationUtils.getMessage(foreignKeyType.getKeyMessage());
        }catch (IllegalArgumentException exception){
            return null;
        }
    }
}
