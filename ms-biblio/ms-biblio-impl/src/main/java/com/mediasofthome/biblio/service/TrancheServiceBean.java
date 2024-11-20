/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TrancheDaoBean;
import com.mediasofthome.biblio.entities.Tranche;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author believe
 */
@Stateless
public class TrancheServiceBean extends GenericServiceBean<Tranche, Long>
        implements TrancheServiceBeanLocal {

    @EJB
    private TrancheDaoBean dao;

    @Override
    protected GenericDAOBean<Tranche, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Tranche e) {
        return e.getId();
    }

}
