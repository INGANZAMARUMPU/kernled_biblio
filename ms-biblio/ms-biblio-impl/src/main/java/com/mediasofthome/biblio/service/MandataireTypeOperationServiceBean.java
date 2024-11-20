/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.MandataireTypeOperationDaoBean;
import com.mediasofthome.biblio.entities.MandataireTypeOperation;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class MandataireTypeOperationServiceBean extends GenericServiceBean<MandataireTypeOperation, Long> implements MandataireTypeOperationServiceBeanLocal {
    
    @EJB
    private MandataireTypeOperationDaoBean dao;

    @Override
    protected GenericDAOBean<MandataireTypeOperation, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(MandataireTypeOperation e) {
        return e.getId();
    }
    
}
