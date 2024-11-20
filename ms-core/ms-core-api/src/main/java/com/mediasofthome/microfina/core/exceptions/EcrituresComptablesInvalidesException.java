package com.mediasofthome.biblio.core.exceptions;

import com.mediasofthome.krnl.exception.BusinessException;

/**
 *
 * @author SAMIE Pyabalo
 * @since 13 sept 2024 à 11:06:04
 */
public class EcrituresComptablesInvalidesException extends BusinessException {

    public EcrituresComptablesInvalidesException() {
        this("Écritures comptables désiquilibrées.");
    }

    public EcrituresComptablesInvalidesException(String message) {
        super(message);
    }

    public EcrituresComptablesInvalidesException(Exception cause) {
        this("Écritures comptables désiquilibrées.", cause);
    }

    public EcrituresComptablesInvalidesException(String message, Exception cause) {
        super(message, cause);
    }

}
