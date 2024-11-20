/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CritereEpargneTrancheDureeDaoBean;
import com.mediasofthome.biblio.entities.CritereEpargneTrancheDuree;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author Mediasoft
 */
@Stateless
public class CritereEpargneTrancheDureeServiceBean extends GenericServiceBean<CritereEpargneTrancheDuree, Long> 
        implements CritereEpargneTrancheDureeServiceBeanLocal{
    
    @EJB
    private CritereEpargneTrancheDureeDaoBean dao;

    @Override
    protected GenericDAOBean<CritereEpargneTrancheDuree, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(CritereEpargneTrancheDuree e) {
        return e.getId();
    }
    
}
