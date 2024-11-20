/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ValeurFraisDaoBean;
import com.mediasofthome.biblio.entities.ValeurFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class ValeurFraisServiceBean extends GenericServiceBean<ValeurFrais, Long> implements ValeurFraisServiceBeanLocal {

    @EJB
    private ValeurFraisDaoBean dao;

    @Override
    protected GenericDAOBean<ValeurFrais, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ValeurFrais e) {
        return e.getId();
    }
}
