/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.service;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.dao.TypeOperationDaoBean;
import com.mediasofthome.biblio.core.entities.TypeOperation;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class TypeOperationServiceBean extends GenericServiceBean<TypeOperation, String> implements TypeOperationServiceBeanLocal {

    @EJB
    private TypeOperationDaoBean dao;

    @Override
    protected GenericDAOBean<TypeOperation, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(TypeOperation e) {
        return e.getCode();
    }

}
