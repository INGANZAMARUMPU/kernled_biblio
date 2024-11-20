package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.domain.ProduitEpargneVo;
import com.mediasofthome.biblio.entities.CritereEpargneSousTypeMembre;
import com.mediasofthome.biblio.entities.ProduitEpargne;
import com.mediasofthome.biblio.entities.SousTypeMembre;
import com.mediasofthome.biblio.entities.TypeMembre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.KEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mawuli
 * @since Lundi 12 juin 2023 à 18:59
 */
@Stateless
public class CritereEpargneSousTypeMembreDaoBean extends GenericDAOBean<CritereEpargneSousTypeMembre, Long> {

    public CritereEpargneSousTypeMembreDaoBean() {
        super(CritereEpargneSousTypeMembre.class);
    }

    public List<ProduitEpargneVo> chargerProduitEpargne(KEntity entite, TypeMembre typeMembre) {
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProduitEpargneVo> query = builder.createQuery(ProduitEpargneVo.class);
        Root<CritereEpargneSousTypeMembre> root = query.from(CritereEpargneSousTypeMembre.class);
        Join<CritereEpargneSousTypeMembre, ProduitEpargne> pe = root.join("produitEpargne", JoinType.LEFT);
        if (Objects.nonNull(typeMembre)) {
            SetJoin<CritereEpargneSousTypeMembre, SousTypeMembre> ssTypeMembres = builder.treat(root.joinSet("sousTypeMembres", JoinType.LEFT), SousTypeMembre.class);
            predicates.add(builder.equal(ssTypeMembres.get("typeMembre"), typeMembre));
        }
        query.multiselect(pe.get("id"), pe.get("code"), pe.get("libelle"));
        query.distinct(true);
        predicates.add(builder.equal(pe.get("entity"), entite));
        predicates.add(builder.equal(pe.get("afficherBiblio"), true));
        query.where(builder.and(predicates.toArray(Predicate[]::new)));
        TypedQuery<ProduitEpargneVo> tquery = em.createQuery(query);
        return tquery.getResultList();
    }
}
