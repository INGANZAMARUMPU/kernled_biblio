/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.SituationMatrimonialeDaoBean;
import com.mediasofthome.biblio.entities.SituationMatrimoniale;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class SituationMatrimonialeServiceBean extends GenericServiceBean<SituationMatrimoniale, Integer> implements SituationMatrimonialeServiceBeanLocal {

    @EJB
    private SituationMatrimonialeDaoBean dao;

    @Override
    protected GenericDAOBean<SituationMatrimoniale, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(SituationMatrimoniale e) {
        return e.getId();
    }

}
