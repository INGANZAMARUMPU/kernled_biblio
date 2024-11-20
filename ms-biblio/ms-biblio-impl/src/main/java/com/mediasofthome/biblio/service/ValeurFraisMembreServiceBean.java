/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ValeurFraisMembreDaoBean;
import com.mediasofthome.biblio.entities.ValeurFraisMembre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class ValeurFraisMembreServiceBean extends GenericServiceBean<ValeurFraisMembre, Long> implements ValeurFraisMembreServiceBeanLocal {

    @EJB
    private ValeurFraisMembreDaoBean dao;

    @Override
    protected GenericDAOBean<ValeurFraisMembre, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ValeurFraisMembre e) {
        return e.getId();
    }

}
