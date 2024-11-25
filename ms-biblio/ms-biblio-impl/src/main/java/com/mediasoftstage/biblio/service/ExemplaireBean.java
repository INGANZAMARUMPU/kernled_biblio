package com.mediasoftstage.biblio.service;

import com.mediasoftstage.biblio.dao.ExemplaireDaoBean;
import com.mediasoftstage.biblio.entities.Exemplaire;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */
@Stateless
public class ExemplaireBean extends GenericServiceBean<Exemplaire, Integer> implements ExemplaireBeanLocal {

    @EJB
    private ExemplaireDaoBean dao;

    @Override
    protected GenericDAOBean<Exemplaire, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(Exemplaire e) {
        return e.getId();
    }

}
