package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ActeurTypeOperationDaoBean;
import com.mediasofthome.biblio.entities.ActeurTypeOperation;
import com.mediasofthome.biblio.entities.Procuration;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

/**
 *
 * @author SAMIÈ Pyabalo
 * @since Mardi, 25 juillet 2023 à 10:05
 */
@Stateless
public class ActeurTypeOperationServiceBean extends GenericServiceBean<ActeurTypeOperation, Long>
        implements ActeurTypeOperationServiceBeanLocal {

    @EJB
    private ActeurTypeOperationDaoBean dao;

    @Override
    protected GenericDAOBean<ActeurTypeOperation, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ActeurTypeOperation e) {
        return e.getId();
    }

    @Override
    public List<ActeurTypeOperation> getAllByProcuration(Long id) {
        return this.dao.getAllByProcuration(id);
    }

    @Override
    public void ajoutAto(List<ActeurTypeOperation> atos, Procuration procuration) {
        List<ActeurTypeOperation> atos2 = new ArrayList<>();
        for (ActeurTypeOperation ato : atos) {
            ato.setProcuration(procuration);
            ato.setActeur(procuration.getMandataire());
            atos2.add(ato);
        }
        this.addAll(atos2);
    }
}
