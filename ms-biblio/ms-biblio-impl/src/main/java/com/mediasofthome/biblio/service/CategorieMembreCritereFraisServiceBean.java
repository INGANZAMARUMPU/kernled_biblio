package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CategorieMembreCritereFraisDaoBean;
import com.mediasofthome.biblio.entities.CategorieMembreCritereFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi 12 juin 2023 à 19:35
 */
@Stateless
public class CategorieMembreCritereFraisServiceBean extends GenericServiceBean<CategorieMembreCritereFrais, Long>
        implements CategorieMembreCritereFraisServiceBeanLocal {

    @EJB
    private CategorieMembreCritereFraisDaoBean dao;

    @Override
    protected GenericDAOBean<CategorieMembreCritereFrais, Long> getDAO() {
        return dao;
    }

    @Override
    public Long getId(CategorieMembreCritereFrais e) {
        return e.getId();
    }

}
