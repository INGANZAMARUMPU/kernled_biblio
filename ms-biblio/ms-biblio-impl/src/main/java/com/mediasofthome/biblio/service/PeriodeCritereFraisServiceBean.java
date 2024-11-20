package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.PeriodeCritereFraisDaoBean;
import com.mediasofthome.biblio.entities.PeriodeCritereFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi 12 juin 2023 à 19:27
 */
@Stateless
public class PeriodeCritereFraisServiceBean extends GenericServiceBean<PeriodeCritereFrais, Long>
        implements PeriodeCritereFraisServiceBeanLocal {

    @EJB
    private PeriodeCritereFraisDaoBean dao;

    @Override
    protected GenericDAOBean<PeriodeCritereFrais, Long> getDAO() {
        return dao;
    }

    @Override
    public Long getId(PeriodeCritereFrais e) {
        return e.getId();
    }

}
