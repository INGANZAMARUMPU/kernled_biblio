/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EpargneGroupeDaoBean;
import com.mediasofthome.biblio.entities.EpargneGroupe;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class EpargneGroupeServiceBean extends GenericServiceBean<EpargneGroupe, Long> implements EpargneGroupeServiceBeanLocal {

    @EJB
    private EpargneGroupeDaoBean dao;

    @Override
    protected GenericDAOBean<EpargneGroupe, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(EpargneGroupe e) {
        return e.getId();
    }

}
