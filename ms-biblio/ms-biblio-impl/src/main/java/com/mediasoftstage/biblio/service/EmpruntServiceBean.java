package com.mediasoftstage.biblio.service;

import com.mediasoftstage.biblio.dao.EmpruntDaoBean;
import com.mediasoftstage.biblio.entities.Emprunt;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */
@Stateless
public class EmpruntServiceBean extends GenericServiceBean<Emprunt, Integer> implements EmpruntBeanLocal {

    @EJB
    private EmpruntDaoBean dao;

    @Override
    protected GenericDAOBean<Emprunt, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(Emprunt e) {
        return e.getId();
    }

}
