/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypeGroupementDaoBean;
import com.mediasofthome.biblio.entities.TypeGroupement;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class TypeGroupementServiceBean extends GenericServiceBean<TypeGroupement, Integer> implements TypeGroupementServiceBeanLocal {

    @EJB
    private TypeGroupementDaoBean dao;

    @Override
    protected GenericDAOBean<TypeGroupement, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(TypeGroupement e) {
        return e.getId();
    }
}
