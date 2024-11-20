/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypeEmployeDaoBean;
import com.mediasofthome.biblio.entities.TypeEmploye;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class TypeEmployeServiceBean extends GenericServiceBean<TypeEmploye, Integer> implements TypeEmployeServiceBeanLocal {

    @EJB
    private TypeEmployeDaoBean dao;

    @Override
    protected GenericDAOBean<TypeEmploye, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(TypeEmploye e) {
        return e.getId();
    }
}
