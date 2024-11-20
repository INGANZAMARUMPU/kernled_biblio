/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CertificatDatDaoBean;
import com.mediasofthome.biblio.dao.DatDaoBean;
import com.mediasofthome.biblio.entities.CertificatDat;
import com.mediasofthome.biblio.entities.Dat;
import com.mediasofthome.biblio.entities.Membre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author mawuli
 */
@Stateless
public class CertificatDatServiceBean extends GenericServiceBean<CertificatDat, Long> implements CertificatDatServiceBeanLocal {

    @EJB
    private CertificatDatDaoBean dao;
    @EJB
    private DatDaoBean datDao;
    @EJB
    private CompteEpargneServiceBeanLocal compteService;

    @Override
    protected GenericDAOBean<CertificatDat, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(CertificatDat e) {
        return e.getId();
    }

    @Override
    public CertificatDat selectionnerUnParId(Long id) {
        return this.dao.selectionnerUnParId(id);
    }

    @Override
    public String genererNumeroCertificat(Membre membre, Entite agence) {
        return RandomStringUtils.randomAlphabetic(8).toUpperCase();
    }

    @Override
    public void createCertificatDat(CertificatDat certificat, Dat dat) {
        certificat.setNumeroCertificat(genererNumeroCertificat(certificat.getMembre(), certificat.getAgence()));
        certificat.setValide(false);
        if(Objects.isNull(certificat.getCompteDAT())){
            certificat.setCompteDAT(compteService.creerCompte(certificat.getMembre(),
                        LocalDate.now(), certificat.getProduitEpargne(), certificat.getAgence()));
        }
        addOne(certificat);
        dat.setExpirer(false);
        dat.setCertificat(certificat);
        datDao.addOne(dat);
    }
}
