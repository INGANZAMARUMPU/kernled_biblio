/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.dao;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.EntityType;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mawuli
 */
@Stateless
public class EntiteDaoBean extends GenericDAOBean<Entite, Integer> {

    public EntiteDaoBean() {
        super(Entite.class);
    }

    public List<Entite> selectionnerTout() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Entite> query = builder.createQuery(Entite.class);
        Root<Entite> root = query.from(Entite.class);
        root.fetch("parent", JoinType.LEFT);
        root.fetch("entityType", JoinType.LEFT);

        TypedQuery<Entite> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    public EntityType canBeRoot(EntityType entityType) {
        String jpql = "SELECT e FROM EntityType e "
                + "WHERE e = :entityType ";
        Query query = this.em.createQuery(jpql);
        query.setParameter("entityType", entityType);
        try {
            return (EntityType) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Entite> entitesByPerson(Entite parent, EntityType entityType) {
        String jpql = "SELECT e FROM Entite e WHERE (e.parent = :parent AND e.entityType = :entityType) OR "
                + "e = :parent";
        Query query = this.em.createQuery(jpql);
        query.setParameter("parent", parent);
        query.setParameter("entityType", entityType);
        return query.getResultList();
    }

    public List<Entite> chargerEntites(Entite... entites) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Entite> query = builder.createQuery(Entite.class);
        Root<Entite> root = query.from(Entite.class);
        List<Predicate> conditions = new ArrayList<>();
        conditions.add(builder.equal(root.get("entityType").get("code"), EntityType.AGENCY_CODE));
        if (entites != null && entites.length > 0) {
            conditions.add(root.get("parent").in(List.of(entites)));
        }
        if (conditions.size() > 1) {
            query.select(root).where(builder.and(conditions.toArray(Predicate[]::new)));
        } else {
            query.select(root).where(conditions.get(0));
        }
        TypedQuery<Entite> tquery = em.createQuery(query);
        return tquery.getResultList();
    }
}
