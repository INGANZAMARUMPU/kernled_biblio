package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.LocalityCritereFraisDaoBean;
import com.mediasofthome.biblio.entities.LocalityCritereFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi 12 juin 2023 à 19:33
 */
@Stateless
public class LocalityCritereFraisServiceBean extends GenericServiceBean<LocalityCritereFrais, Long>
        implements LocalityCritereFraisServiceBeanLocal {

    @EJB
    private LocalityCritereFraisDaoBean dao;

    @Override
    protected GenericDAOBean<LocalityCritereFrais, Long> getDAO() {
        return dao;
    }

    @Override
    public Long getId(LocalityCritereFrais e) {
        return e.getId();
    }

}
