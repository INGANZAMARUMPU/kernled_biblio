/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.entities.CertificatDat;
import com.mediasofthome.biblio.entities.Dat;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author mawuli
 */
@Stateless
public class DatDaoBean extends GenericDAOBean<Dat, Long> {

    public DatDaoBean() {
        super(Dat.class);
    }

    public Dat selectDatEnCours(CertificatDat certificat) {
        
        String requete = """
                            select d from Dat d where d.expirer = false and d.certificat.id = :certificat
                         """;
        TypedQuery<Dat> query = em.createQuery(requete, Dat.class);
        query.setParameter("certificat", certificat.getId());
        return query.getSingleResult();
    }
}
