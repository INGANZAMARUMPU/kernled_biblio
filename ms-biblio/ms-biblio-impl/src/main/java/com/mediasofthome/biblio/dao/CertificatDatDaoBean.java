/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.entities.CertificatDat;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author mawuli
 */
@Stateless
public class CertificatDatDaoBean extends GenericDAOBean<CertificatDat, Long> {

    public CertificatDatDaoBean() {
        super(CertificatDat.class);
    }

    public CertificatDat selectionnerUnParId(Long id) {
        String sql = """
                        select c from CertificatDat c left join fetch c.compteEpargne ce
                        left join fetch c.compteDAT ced
                        left join fetch c.compteVirement cev
                        left join fetch c.produitEpargne pe
                        left join fetch c.membre m
                        left join fetch c.periodicite pr
                        left join fetch c.agence a
                        where c.id = :idpe
                     """;
        TypedQuery<CertificatDat> peq = this.em.createQuery(sql, CertificatDat.class);
        peq.setParameter("idpe", id);
        return peq.getSingleResult();
    }
}
