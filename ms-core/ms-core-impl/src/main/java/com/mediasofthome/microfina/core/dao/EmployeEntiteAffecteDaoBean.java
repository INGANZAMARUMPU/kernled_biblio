/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.dao;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.biblio.core.entities.Employe;
import com.mediasofthome.biblio.core.entities.EmployeEntiteAffecte;
import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 *
 * @author mawuli
 */
@Stateless
public class EmployeEntiteAffecteDaoBean extends GenericDAOBean<EmployeEntiteAffecte, Long> {

    public EmployeEntiteAffecteDaoBean() {
        super(EmployeEntiteAffecte.class);
    }

    public List<KEntity> chargerEntites(FilterParams filterParams) {

        String sql = "SELECT e.entite FROM EmployeEntiteAffecte e "
                + filterParams.queryChunkWithWhere();
        TypedQuery<KEntity> query = em.createQuery(sql, KEntity.class);
        filterParams.setQueryParams(query);
        return query.getResultList();
    }

    public List<KEntity> chargerEntitesParEmploye(Employe employe) {
        String sql = "SELECT e.entite FROM EmployeEntiteAffecte e "
                + "WHERE e.employe = :employe";
        TypedQuery<KEntity> query = em.createQuery(sql, KEntity.class)
                .setParameter("employe", employe);
        return query.getResultList();
    }
}
