package br.com.present.commons.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataIntegrityViolationType {

    CLA_DISID("dataIntegrityViolationType.err.claDisId"),
    CLA_GROID("dataIntegrityViolationType.err.claGroId"),
    EVE_USEID("dataIntegrityViolationType.err.eveUseId");

    private final String keyMessage;
}
