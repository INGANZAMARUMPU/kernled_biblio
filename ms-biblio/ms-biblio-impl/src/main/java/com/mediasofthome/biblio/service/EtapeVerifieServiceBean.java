/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;
import com.mediasofthome.biblio.dao.EtapeVerifieDaoBean;
import com.mediasofthome.biblio.entities.EtapeVerifie;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author TOKPE
 */
@Stateless
public class EtapeVerifieServiceBean extends GenericServiceBean<EtapeVerifie, Long> implements EtapeVerifieServiceBeanLocal{
    @EJB
    private EtapeVerifieDaoBean dao;

    @Override
    protected GenericDAOBean<EtapeVerifie, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(EtapeVerifie e) {
        return e.getId();
    }
}