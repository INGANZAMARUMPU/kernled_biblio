package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.MembreAttributDaoBean;
import com.mediasofthome.biblio.entities.MembreAttribut;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

/**
 *
 * @author SAMIE Pyabalo
 * <samiepyabalo@gmail.com>
 *
 */
@Stateless
public class MembreAttributServiceBean extends GenericServiceBean<MembreAttribut, Long> implements MembreAttributServiceBeanLocal {

    @EJB
    private MembreAttributDaoBean dao;

    @Override
    protected GenericDAOBean<MembreAttribut, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(MembreAttribut e) {
        return e.getId();
    }

    @Override
    public MembreAttribut updateAttribut(List<MembreAttribut> attributs) {
        MembreAttribut ma = new MembreAttribut();
        for (MembreAttribut a : attributs) {
            ma = this.updateOne(a);
        }
        return ma;
    }
}
