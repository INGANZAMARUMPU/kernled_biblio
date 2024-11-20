package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TrancheMontantDaoBean;
import com.mediasofthome.biblio.entities.TrancheMontant;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;

public class TrancheMontantServiceBean extends GenericServiceBean<TrancheMontant, Long>
        implements TrancheMontantServiceBeanLocal {

    @EJB
    private TrancheMontantDaoBean dao;

    @Override
    protected GenericDAOBean<TrancheMontant, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TrancheMontant e) {
        return e.getId();
    }

}
