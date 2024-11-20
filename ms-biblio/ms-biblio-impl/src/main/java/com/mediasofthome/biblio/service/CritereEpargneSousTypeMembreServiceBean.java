package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CritereEpargneSousTypeMembreDaoBean;
import com.mediasofthome.biblio.domain.ProduitEpargneVo;
import com.mediasofthome.biblio.entities.CritereEpargneSousTypeMembre;
import com.mediasofthome.biblio.entities.TypeMembre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi 12 juin 2023 à 19:42
 */
@Stateless
public class CritereEpargneSousTypeMembreServiceBean extends GenericServiceBean<CritereEpargneSousTypeMembre, Long>
        implements CritereEpargneSousTypeMembreServiceBeanLocal {

    @EJB
    private CritereEpargneSousTypeMembreDaoBean dao;

    @Override
    protected GenericDAOBean<CritereEpargneSousTypeMembre, Long> getDAO() {
        return dao;
    }

    @Override
    public Long getId(CritereEpargneSousTypeMembre e) {
        return e.getId();
    }

    @Override
    public List<ProduitEpargneVo> chargerProduitEpargne(KEntity entite, TypeMembre typeMembre) {
        return dao.chargerProduitEpargne(entite, typeMembre);
    }

}
