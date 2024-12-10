package com.mediasoftstage.biblio.service;

import com.mediasoftstage.biblio.dao.LivreDaoBean;
import com.mediasoftstage.biblio.entities.Livre;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */
@Stateless
public class LivreServiceBean extends GenericServiceBean<Livre, Integer> implements LivreBeanLocal {

    @EJB
    private LivreDaoBean dao;

    @Override
    protected GenericDAOBean<Livre, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(Livre e) {
        return e.getId();
    }

}
