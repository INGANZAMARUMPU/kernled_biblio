package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProcessusBiblioDaoBean;
import com.mediasofthome.biblio.entities.ProcessusBiblio;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi, 19 juin 2023 à 14:44
 */
@Stateless
public class ProcessusBiblioServiceBean extends GenericServiceBean<ProcessusBiblio, Long> implements ProcessusBiblioServiceBeanLocal {

    @EJB
    private ProcessusBiblioDaoBean dao;
    
    @Override
    protected GenericDAOBean<ProcessusBiblio, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ProcessusBiblio e) {
        return e.getId();
    }

    @Override
    public ProcessusBiblio getOne(Long id) {
      ProcessusBiblio processusBiblio = this.dao.getOne(id);
      processusBiblio.getEntite();
      return processusBiblio;
    }
}
