package com.mediasoftstage.biblio.service;

import com.mediasoftstage.biblio.dao.AbonnementDaoBean;
import com.mediasoftstage.biblio.entities.Abonnement;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */
@Stateless
public class AbonnementBean extends GenericServiceBean<Abonnement, Integer> implements AbonnementBeanLocal {

    @EJB
    private AbonnementDaoBean dao;

    @Override
    protected GenericDAOBean<Abonnement, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(Abonnement e) {
        return e.getId();
    }

}
