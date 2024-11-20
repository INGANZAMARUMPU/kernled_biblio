/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.domain.CompteEpargneVo;
import com.mediasofthome.biblio.domain.StatisticBiblioDTO;
import com.mediasofthome.biblio.entities.CompteEpargne;
import com.mediasofthome.biblio.entities.ProduitEpargne;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author mawuli
 */
@Stateless
public class CompteEpargneDaoBean extends GenericDAOBean<CompteEpargne, Long> {

    public CompteEpargneDaoBean() {
        super(CompteEpargne.class);
    }

    public List<CompteEpargne> selectionnerTout() {
        String jpql = "SELECT ce FROM CompteEpargne ce "
                + "LEFT JOIN FETCH ce.produitEpargne ";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    public List<CompteEpargneVo> selectionnerVo() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CompteEpargneVo> cceq = cb.createQuery(CompteEpargneVo.class);
        Root<CompteEpargne> root = cceq.from(CompteEpargne.class);
        cceq.multiselect(root.get("id"), root.get("numeroCompte"), root.get("intitule"));
        TypedQuery<CompteEpargneVo> query = em.createQuery(cceq);
        return query.getResultList();
    }

    public List<StatisticBiblioDTO> getLastFiveAcount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StatisticBiblioDTO> ccce = cb.createQuery(StatisticBiblioDTO.class);
        Root<CompteEpargne> root = ccce.from(CompteEpargne.class);
        ccce.select(cb.construct(
                StatisticBiblioDTO.class,
                root.get("id"),
                root.get("numeroCompte"),
                root.get("dateDeblocage"),
                root.get("membre")
        //                jem.get("nom")
        ));
        TypedQuery<StatisticBiblioDTO> query = em.createQuery(ccce);
        return query.setMaxResults(4).getResultList();
    }

    public List<CompteEpargne> ensembleDeComptesFermer() {
        String jpql = "SELECT ce FROM CompteEpargne ce WHERE ce.ferme = True";
        TypedQuery<CompteEpargne> query = this.em.createQuery(jpql, CompteEpargne.class);
        return query.getResultList();
    }
    
    public List<CompteEpargne> filtrageDeCompteParEntiteConnecte(Entite e){
        String jpql = "SELECT c FROM CompteEpargne c WHERE c.ferme == True AND c.agence =: e";
        TypedQuery<CompteEpargne> query = this.em.createQuery(jpql, CompteEpargne.class);
        return query.getResultList();
    }

    public List<CompteEpargneVo> selectionnerVoParMembre(Long idMembre) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CompteEpargneVo> cceq = cb.createQuery(CompteEpargneVo.class);
        Root<CompteEpargne> root = cceq.from(CompteEpargne.class);
        cceq.multiselect(root.get("id"), root.get("numeroCompte"), root.get("intitule"))
                .where(cb.equal(root.get("membre").get("id"), cb.parameter(Long.class, "idm")));
        TypedQuery<CompteEpargneVo> query = em.createQuery(cceq);
        query.setParameter("idm", idMembre);
        return query.getResultList();
    }

    public List<CompteEpargneVo> selectionnerVoParMembre(Long idMembre, Class<? extends ProduitEpargne> classe) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CompteEpargneVo> cceq = cb.createQuery(CompteEpargneVo.class);
        Root<CompteEpargne> root = cceq.from(CompteEpargne.class);
        cb.treat(root.join("produitEpargne"), classe);
        cceq.multiselect(root.get("id"), root.get("numeroCompte"), root.get("intitule"))
                .where(cb.equal(root.get("membre").get("id"), cb.parameter(Long.class, "idm")));
        TypedQuery<CompteEpargneVo> query = em.createQuery(cceq);
        query.setParameter("idm", idMembre);
        return query.getResultList();
    }

    public List<CompteEpargneVo> selectionnerVoParMembreSans(Long idMembre, Class<? extends ProduitEpargne> classe) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CompteEpargneVo> cceq = cb.createQuery(CompteEpargneVo.class);
        Root<CompteEpargne> root = cceq.from(CompteEpargne.class);
        Join<CompteEpargne, ProduitEpargne> jep = root.join("produitEpargne");
        cceq.multiselect(root.get("id"), root.get("numeroCompte"), root.get("intitule"))
                .where(cb.and(cb.notEqual(jep.type(), classe),
                        cb.equal(root.get("membre").get("id"), cb.parameter(Long.class, "idm"))));
        TypedQuery<CompteEpargneVo> query = em.createQuery(cceq);
        query.setParameter("idm", idMembre);
        return query.getResultList();
    }

    public List<CompteEpargneVo> chargerCompteVo(Entite entite) {
        String requete = """
                            select ce.id, 
                                   ce.numero_compte, 
                                   ce.intitule, 
                                   ce.id_membre, 
                                   m.numero_membre, 
                                   case when m.dtype = 'MP' then concat(m.nom_membre,' ', m.prenom_membre) else m.raison_sociale end as nom,
                                   coalesce(e.credit,0) as credit,
                                   coalesce(e.debit,0) as debit,
                                   coalesce(tb.bloque ,0) as bloque,
                                   coalesce(tb.debloque,0) as debloque
                            from compte_epargnes ce 
                            left join membres m on ce.id_membre  = m.id
                            left join (select tb2.id_compte_epargne , sum(tb2.montant_bloque) bloque , sum(tb2.montant_libere) debloque from trace_blocages tb2 group by tb2.id_compte_epargne) tb ON tb.id_compte_epargne = ce.id
                            left join (select ep.id_compte_epargne , sum(ep.montant_credit) credit , sum(ep.montant_debit) debit from epargnes ep group by ep.id_compte_epargne) e on ce.id = e.id_compte_epargne 
                            left join produits p on ce.id_produit_epargne = p.id
                         """;
        if (Objects.nonNull(entite) && entite.getId() != null) {
            requete += " where ce.id_agence = ?1";
        }
        requete += " order  by ce.id";
        Query query = em.createNativeQuery(requete, "CompteVoMapping");
        if (Objects.nonNull(entite) && entite.getId() != null) {
            query.setParameter(1, entite.getId());
        }
        return query.getResultList();
    }

    public List<CompteEpargneVo> chargerCompteVoParParent(Entite entite) {
        String requete = """
                            select ce.id, 
                                   ce.numero_compte, 
                                   ce.intitule, 
                                   ce.id_membre, 
                                   m.numero_membre, 
                                   case when m.dtype = 'MP' then concat(m.nom_membre,' ', m.prenom_membre) else m.raison_sociale end as nom,
                                   coalesce(e.credit,0) as credit,
                                   coalesce(e.debit,0) as debit,
                                   coalesce(tb.bloque ,0) as bloque,
                                   coalesce(tb.debloque,0) as debloque
                            from compte_epargnes ce
                            join core_entities e ce.agence = e.id
                            left join membres m on ce.id_membre  = m.id
                            left join (select tb2.id_compte_epargne , sum(tb2.montant_bloque) bloque , sum(tb2.montant_libere) debloque from trace_blocages tb2 group by tb2.id_compte_epargne) tb ON tb.id_compte_epargne = ce.id
                            left join (select ep.id_compte_epargne , sum(ep.montant_credit) credit , sum(ep.montant_debit) debit from epargnes ep group by ep.id_compte_epargne) e on ce.id = e.id_compte_epargne 
                            left join produits p on ce.id_produit_epargne = p.id
                         """;
        if (Objects.nonNull(entite) && entite.getId() != null) {
            requete += " where e.parent_id = ?1";
        }
        requete += " order  by ce.id";
        Query query = em.createNativeQuery(requete, "CompteVoMapping");
        if (Objects.nonNull(entite) && entite.getId() != null) {
            query.setParameter(1, entite.getId());
        }
        return query.getResultList();
    }

    public List<CompteEpargneVo> chargerCompteVoParMembre(Long membre, String dtype) {
        String requete = """
                            select ce.id, 
                                   ce.numero_compte, 
                                   ce.intitule, 
                                   ce.id_membre, 
                                   m.numero_membre, 
                                   case when m.dtype = 'MP' then concat(m.nom_membre,' ', m.prenom_membre) else m.raison_sociale end as nom,
                                   coalesce(e.credit,0) as credit,
                                   coalesce(e.debit,0) as debit,
                                   coalesce(tb.bloque ,0) as bloque,
                                   coalesce(tb.debloque,0) as debloque
                            from compte_epargnes ce 
                            left join membres m on ce.id_membre  = m.id
                            left join (select tb2.id_compte_epargne , sum(tb2.montant_bloque) bloque , sum(tb2.montant_libere) debloque from trace_blocages tb2 group by tb2.id_compte_epargne) tb ON tb.id_compte_epargne = ce.id
                            left join (select ep.id_compte_epargne , sum(ep.montant_credit) credit , sum(ep.montant_debit) debit from epargnes ep group by ep.id_compte_epargne) e on ce.id = e.id_compte_epargne 
                            left join produits p on ce.id_produit_epargne = p.id
                         """;
        if (membre != null) {
            requete += " where ce.id_membre = ?1";
        }
        if (StringUtils.isNotBlank(dtype)) {
            if (membre != null) {
                requete += " and p.dtype = ?2";
            } else {
                requete += " where p.dtype = ?2";
            }
        }
        requete += " order  by ce.id";
        Query query = em.createNativeQuery(requete, "CompteVoMapping");
        if (membre != null) {
            query.setParameter(1, membre);
        }
        if (StringUtils.isNotBlank(dtype)) {
            query.setParameter(2, dtype);
        }
        return query.getResultList();

    }

    public List<CompteEpargneVo> chargerCompteVoParMembreSans(Long membre, String dtype) {
        String requete = """
                            select ce.id, 
                                   ce.numero_compte, 
                                   ce.intitule, 
                                   ce.id_membre, 
                                   m.numero_membre, 
                                   case when m.dtype = 'MP' then concat(m.nom_membre,' ', m.prenom_membre) else m.raison_sociale end as nom,
                                   coalesce(e.credit,0) as credit,
                                   coalesce(e.debit,0) as debit,
                                   coalesce(tb.bloque ,0) as bloque,
                                   coalesce(tb.debloque,0) as debloque
                            from compte_epargnes ce 
                            left join membres m on ce.id_membre  = m.id
                            left join (select tb2.id_compte_epargne , sum(tb2.montant_bloque) bloque , sum(tb2.montant_libere) debloque from trace_blocages tb2 group by tb2.id_compte_epargne) tb ON tb.id_compte_epargne = ce.id
                            left join (select ep.id_compte_epargne , sum(ep.montant_credit) credit , sum(ep.montant_debit) debit from epargnes ep group by ep.id_compte_epargne) e on ce.id = e.id_compte_epargne 
                            left join produits p on ce.id_produit_epargne = p.id
                         """;
        if (membre != null) {
            requete += " where ce.id_membre = ?1";
        }
        if (StringUtils.isNotBlank(dtype)) {
            if (membre != null) {
                requete += " and p.dtype <> ?2";
            } else {
                requete += " where p.dtype <> ?2";
            }
        }
        requete += " order  by ce.id";
        Query query = em.createNativeQuery(requete, "CompteVoMapping");
        if (membre != null) {
            query.setParameter(1, membre);
        }
        if (StringUtils.isNotBlank(dtype)) {
            query.setParameter(2, dtype);
        }
        return query.getResultList();
    }

    public CompteEpargneVo chargerCompteVoParId(Long id) {
        try {
            String requete = """
                            select ce.id, 
                                   ce.numero_compte, 
                                   ce.intitule, 
                                   ce.id_membre, 
                                   m.numero_membre, 
                                   case when m.dtype = 'MP' then concat(m.nom_membre,' ', m.prenom_membre) else m.raison_sociale end as nom,
                                   coalesce(e.credit,0) as credit,
                                   coalesce(e.debit,0) as debit,
                                   coalesce(tb.bloque ,0) as bloque,
                                   coalesce(tb.debloque,0) as debloque
                            from compte_epargnes ce 
                            left join membres m on ce.id_membre  = m.id
                            left join (select tb2.id_compte_epargne , sum(tb2.montant_bloque) bloque , sum(tb2.montant_libere) debloque from trace_blocages tb2 group by tb2.id_compte_epargne) tb ON tb.id_compte_epargne = ce.id
                            left join (select ep.id_compte_epargne , sum(ep.montant_credit) credit , sum(ep.montant_debit) debit from epargnes ep group by ep.id_compte_epargne) e on ce.id = e.id_compte_epargne 
                            left join produits p on ce.id_produit_epargne = p.id
                         """;
            if (id != null) {
                requete += " where ce.id = ?1";
            }
            requete += " order  by ce.id";
            Query query = em.createNativeQuery(requete, "CompteVoMapping");
            if (id != null) {
                query.setParameter(1, id);
            }
            return (CompteEpargneVo) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
