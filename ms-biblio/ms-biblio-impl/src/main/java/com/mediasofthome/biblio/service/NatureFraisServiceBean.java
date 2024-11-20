/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.NatureFraisDaoBean;
import com.mediasofthome.biblio.entities.NatureFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class NatureFraisServiceBean extends GenericServiceBean<NatureFrais, Integer> implements NatureFraisServiceBeanLocal {

    @EJB
    private NatureFraisDaoBean dao;

    @Override
    protected GenericDAOBean<NatureFrais, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(NatureFrais e) {
        return e.getId();
    }
}
