package com.mediasoftstage.biblio.service;

import com.mediasoftstage.biblio.dao.EmprunteurDaoBean;
import com.mediasoftstage.biblio.entities.Emprunteur;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */
@Stateless
public class EmprunteurBean extends GenericServiceBean<Emprunteur, Integer> implements EmprunteurBeanLocal {

    @EJB
    private EmprunteurDaoBean dao;

    @Override
    protected GenericDAOBean<Emprunteur, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(Emprunteur e) {
        return e.getId();
    }

}
