package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.entities.ActeurTypeOperation;
import com.mediasofthome.biblio.entities.Procuration;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;

import java.util.List;

/**
 *
 * @author SAMIÈ Pyabalo
 * @since Mardi, 25 juillet 2023 à 10:25
 */
public interface ActeurTypeOperationServiceBeanLocal extends GenericServiceBeanLocal<ActeurTypeOperation, Long> {

    /**
     *
     * @param id
     * @return
     */
    List<ActeurTypeOperation> getAllByProcuration(Long id);

    void ajoutAto(List<ActeurTypeOperation> atos, Procuration procuration);
}
