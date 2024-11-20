/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ChargeDatDaoBean;
import com.mediasofthome.biblio.entities.ChargeDat;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class ChargeDatServiceBean extends GenericServiceBean<ChargeDat, Long> implements ChargeDatServiceBeanLocal {
    
    @EJB
    private ChargeDatDaoBean dao;

    @Override
    protected GenericDAOBean<ChargeDat, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ChargeDat e) {
        return e.getId();
    }
    
}
