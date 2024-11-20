/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CotisationPlanEpargneDaoBean;
import com.mediasofthome.biblio.entities.CotisationPlanEpargne;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class CotisationPlanEpargneServiceBean extends GenericServiceBean<CotisationPlanEpargne, Long> implements CotisationPlanEpargneServiceBeanLocal {
    
    @EJB
    private CotisationPlanEpargneDaoBean dao;

    @Override
    protected GenericDAOBean<CotisationPlanEpargne, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(CotisationPlanEpargne e) {
        return e.getId();
    }
    
}
