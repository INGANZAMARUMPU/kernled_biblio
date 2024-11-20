package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.SousTypeMembreCritereFraisDaoBean;
import com.mediasofthome.biblio.entities.SousTypeMembreCritereFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi 12 juin 2023 à 19:42
 */
@Stateless
public class SousTypeMembreCritereFraisServiceBean extends GenericServiceBean<SousTypeMembreCritereFrais, Long>
        implements SousTypeMembreCritereFraisServiceBeanLocal {

    @EJB
    private SousTypeMembreCritereFraisDaoBean dao;

    @Override
    protected GenericDAOBean<SousTypeMembreCritereFrais, Long> getDAO() {
        return dao;
    }

    @Override
    public Long getId(SousTypeMembreCritereFrais e) {
        return e.getId();
    }

}
