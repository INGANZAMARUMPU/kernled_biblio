/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TrancheDureeDaoBean;
import com.mediasofthome.biblio.entities.TrancheDuree;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author believe
 */
@Stateless
public class TrancheDureeServiceBean extends GenericServiceBean<TrancheDuree, Long>
        implements TrancheDureeServiceBeanLocal {

    @EJB
    private TrancheDureeDaoBean dao;

    @Override
    protected GenericDAOBean<TrancheDuree, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TrancheDuree e) {
        return e.getId();
    }

}
