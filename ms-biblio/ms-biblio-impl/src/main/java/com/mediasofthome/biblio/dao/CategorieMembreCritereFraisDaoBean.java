package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.entities.CategorieMembreCritereFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;

/**
 /**
 *
 * @author SAMIE Pyabalo
 * @since Lundi 12 juin 2023 à 19:31
 */
@Stateless
public class CategorieMembreCritereFraisDaoBean extends GenericDAOBean<CategorieMembreCritereFrais, Long>{
    
    public CategorieMembreCritereFraisDaoBean() {
        super(CategorieMembreCritereFrais.class);
    }
    
}
