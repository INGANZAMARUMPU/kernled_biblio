package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProfessionCritereFraisDaoBean;
import com.mediasofthome.biblio.entities.ProfessionCritereFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi 12 juin 2023 à 19:25
 */
@Stateless
public class ProfessionCritereFraisServiceBean extends GenericServiceBean<ProfessionCritereFrais, Long>
        implements ProfessionCritereFraisServiceBeanLocal {

    @EJB
    private ProfessionCritereFraisDaoBean dao;

    @Override
    protected GenericDAOBean<ProfessionCritereFrais, Long> getDAO() {
        return dao;
    }

    @Override
    public Long getId(ProfessionCritereFrais e) {
        return e.getId();
    }

}
