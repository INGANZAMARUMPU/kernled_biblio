/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.DatDaoBean;
import com.mediasofthome.biblio.entities.CertificatDat;
import com.mediasofthome.biblio.entities.Dat;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class DatServiceBean extends GenericServiceBean<Dat, Long> implements DatServiceBeanLocal {
    
    @EJB
    private DatDaoBean dao;

    @Override
    protected GenericDAOBean<Dat, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Dat e) {
        return e.getId();
    }

    @Override
    public Dat selectDatEnCours(CertificatDat certificat) {
        return dao.selectDatEnCours(certificat);
    }
}
