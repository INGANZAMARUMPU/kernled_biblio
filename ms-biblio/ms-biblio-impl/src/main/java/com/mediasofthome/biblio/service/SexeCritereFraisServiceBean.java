package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.SexeCritereFraisDaoBean;
import com.mediasofthome.biblio.entities.SexeCritereFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi 12 juin 2023 à 19:20
 */
@Stateless
public class SexeCritereFraisServiceBean extends GenericServiceBean<SexeCritereFrais, Long> 
        implements SexeCritereFraisServiceBeanLocal{

    @EJB
    private SexeCritereFraisDaoBean dao;

    @Override
    protected GenericDAOBean<SexeCritereFrais, Long> getDAO() {
        return dao;
    }

    @Override
    public Long getId(SexeCritereFrais e) {
        return e.getId();
    }

}
