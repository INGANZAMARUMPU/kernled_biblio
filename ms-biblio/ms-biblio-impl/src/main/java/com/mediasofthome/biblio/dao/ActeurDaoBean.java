/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.entities.Acteur;
import com.mediasofthome.biblio.entities.Membre;
import com.mediasofthome.biblio.entities.TypeActeur;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.List;

/**
 *
 * @author mawuli
 */
@Stateless
public class ActeurDaoBean extends GenericDAOBean<Acteur, Long> {

    public ActeurDaoBean() {
        super(Acteur.class);
    }

    public Acteur getById(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Acteur> cq = cb.createQuery(Acteur.class);
        Root<Acteur> root = cq.from(Acteur.class);
        root.fetch("adresse", JoinType.LEFT);
        root.fetch("sexe", JoinType.LEFT);
        root.fetch("profession", JoinType.LEFT);
        root.fetch("nationalite", JoinType.LEFT);
        ParameterExpression<Long> pid = cb.parameter(Long.class);
        cq.where(cb.equal(root.get("id"), pid));

        TypedQuery<Acteur> q = em.createQuery(cq);
        q.setParameter(pid, id);
        return q.getSingleResult();
    }

    public List<Acteur> selectionnerTout() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Acteur> cq = cb.createQuery(Acteur.class);
        Root<Acteur> root = cq.from(Acteur.class);
        root.fetch("adresse", JoinType.LEFT);
        root.fetch("sexe", JoinType.LEFT);
        root.fetch("profession", JoinType.LEFT);
        root.fetch("nationalite", JoinType.LEFT);

        TypedQuery<Acteur> q = em.createQuery(cq);
        return q.getResultList();
    }

    public List<Acteur> selectionnerTout(Long idMembre) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Acteur> cq = cb.createQuery(Acteur.class);
        Root<Acteur> root = cq.from(Acteur.class);
        root.fetch("adresse", JoinType.LEFT);
        root.fetch("sexe", JoinType.LEFT);
        root.fetch("profession", JoinType.LEFT);
        root.fetch("nationalite", JoinType.LEFT);

        Join<Acteur, Membre> jmemb = root.join("membre");

        ParameterExpression<Long> pid = cb.parameter(Long.class);
        cq.where(cb.equal(jmemb.get("id"), pid));

        TypedQuery<Acteur> q = em.createQuery(cq);
        q.setParameter(pid, idMembre);
        return q.getResultList();
    }

    public List<Acteur> getMandataires(String prefixe, TypeActeur typeActeur) {
        String jpql = "SELECT DISTINCT a FROM Acteur a "
                + "LEFT JOIN FETCH a.profession "
                + "LEFT JOIN FETCH a.poste "
                + "LEFT JOIN FETCH a.secteurActivite "
                + "LEFT JOIN FETCH a.brancheActivite "
                + "LEFT JOIN FETCH a.nationalite "
                + "LEFT JOIN FETCH a.piece "
                + "LEFT JOIN FETCH a.piece.typePiece "
                + "LEFT JOIN FETCH a.piece.paysDelivrance "
                + "LEFT JOIN FETCH a.typeFiliation "
                + "LEFT JOIN FETCH a.typeActeur "
                + "LEFT JOIN FETCH a.locality "
                + "LEFT JOIN FETCH a.membre "
                + "LEFT JOIN FETCH a.entite "
                + "WHERE a.typeActeur = :ta "
                + "AND (UPPER(a.nom) LIKE UPPER(:prefixe) OR UPPER(a.prenom) LIKE UPPER(:prefixe)) "
                + "ORDER BY a.nom ASC ";
        Query query = this.em.createQuery(jpql);
        query.setParameter("prefixe", "%" + prefixe + "%")
                .setParameter("prefixe", "%" + prefixe + "%");
        query.setParameter("ta", typeActeur);
        return query.getResultList();
    }
}
