/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EpargneGroupeDetailDaoBean;
import com.mediasofthome.biblio.entities.EpargneGroupeDetail;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class EpargneGroupeDetailServiceBean extends GenericServiceBean<EpargneGroupeDetail, Long> implements EpargneGroupeDetailServiceBeanLocal {

    @EJB
    private EpargneGroupeDetailDaoBean dao;

    @Override
    protected GenericDAOBean<EpargneGroupeDetail, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(EpargneGroupeDetail e) {
        return e.getId();
    }

}
