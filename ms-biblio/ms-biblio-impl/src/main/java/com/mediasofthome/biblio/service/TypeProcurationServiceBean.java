/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypeProcurationDaoBean;
import com.mediasofthome.biblio.entities.TypeProcuration;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author sama
 */
@Stateless
public class TypeProcurationServiceBean extends GenericServiceBean<TypeProcuration, Long> implements TypeProcurationServiceBeanLocal {

    @EJB
    private TypeProcurationDaoBean dao;
    
    @Override
    protected GenericDAOBean<TypeProcuration, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TypeProcuration e) {
        return e.getId();
    }
    
}
