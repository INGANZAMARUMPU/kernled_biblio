/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypeGuichetDaoBean;
import com.mediasofthome.biblio.entities.TypeGuichet;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class TypeGuichetServiceBean extends GenericServiceBean<TypeGuichet, Long> implements TypeGuichetServiceBeanLocal {

    @EJB
    private TypeGuichetDaoBean dao;

    @Override
    protected GenericDAOBean<TypeGuichet, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TypeGuichet e) {
        return e.getId();
    }

}
