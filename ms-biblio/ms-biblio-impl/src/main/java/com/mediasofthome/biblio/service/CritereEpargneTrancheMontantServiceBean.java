 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CritereEpargneTrancheMontantDaoBean;
import com.mediasofthome.biblio.entities.CritereEpargneTrancheMontant;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author Mediasoft
 */
@Stateless
public class CritereEpargneTrancheMontantServiceBean extends GenericServiceBean<CritereEpargneTrancheMontant, Long> 
        implements CritereEpargneTrancheMontantServiceBeanLocal{

    @EJB
    private CritereEpargneTrancheMontantDaoBean dao;
    
    @Override
    protected GenericDAOBean<CritereEpargneTrancheMontant, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(CritereEpargneTrancheMontant e) {
        return e.getId();
    }
    
}
