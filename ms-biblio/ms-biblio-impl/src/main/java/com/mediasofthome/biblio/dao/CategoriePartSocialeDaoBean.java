/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.entities.CategoriePartSociale;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class CategoriePartSocialeDaoBean extends GenericDAOBean<CategoriePartSociale, Integer> {

    public CategoriePartSocialeDaoBean() {
        super(CategoriePartSociale.class);
    }
}
