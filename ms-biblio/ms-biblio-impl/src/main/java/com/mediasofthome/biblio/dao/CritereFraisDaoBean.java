package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.entities.CritereFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;

import java.util.List;

/**
 *
 * @author SAMIE Pyabalo
 * @since Mardi 13 juin 2023 à 11:52
 */
@Stateless
public class CritereFraisDaoBean extends GenericDAOBean<CritereFrais, Long> {

    public CritereFraisDaoBean() {
        super(CritereFrais.class);
    }

    @Override
    public List<CritereFrais> getAll() {
        String jpql = "SELECT cf FROM CritereFrais cf "
                + "FETCH LEFT JOIN cf.critereFrais "
                + "FETCH LEFT JOIN cf.frais ";
        
        Query query = em.createQuery(jpql);

        return query.getResultList();
    }

}
