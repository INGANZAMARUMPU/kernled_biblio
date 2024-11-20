package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProcessusMetierDaoBean;
import com.mediasofthome.biblio.entities.ProcessusMetier;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi, 19 juin 2023 à 14:40
 */
@Stateless
public class ProcessusMetierServiceBean extends GenericServiceBean<ProcessusMetier, Long>
        implements ProcessusMetierServiceBeanLocal {

    @EJB
    private ProcessusMetierDaoBean dao;

    @Override
    protected GenericDAOBean<ProcessusMetier, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ProcessusMetier e) {
        return e.getId();
    }

}
