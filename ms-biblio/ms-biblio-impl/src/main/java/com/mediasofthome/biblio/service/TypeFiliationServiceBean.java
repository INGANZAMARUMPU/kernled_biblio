/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypeFiliationDaoBean;
import com.mediasofthome.biblio.entities.TypeFiliation;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class TypeFiliationServiceBean extends GenericServiceBean<TypeFiliation, Integer> implements TypeFiliationServiceBeanLocal {

    @EJB
    private TypeFiliationDaoBean dao;
    
    @Override
    protected GenericDAOBean getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(TypeFiliation e) {
        return e.getId();
    }
    
}
