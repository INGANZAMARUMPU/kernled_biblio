/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypeMembreDaoBean;
import com.mediasofthome.biblio.entities.TypeMembre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class TypeMembreServiceBean extends GenericServiceBean<TypeMembre, Integer> implements TypeMembreServiceBeanLocal {

    @EJB
    private TypeMembreDaoBean dao;

    @Override
    protected GenericDAOBean<TypeMembre, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(TypeMembre e) {
        return e.getId();
    }
}
