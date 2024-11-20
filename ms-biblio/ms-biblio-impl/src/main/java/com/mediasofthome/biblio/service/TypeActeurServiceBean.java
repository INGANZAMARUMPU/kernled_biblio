/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypeActeurDaoBean;
import com.mediasofthome.biblio.entities.TypeActeur;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author mawuli
 */
@Stateless
public class TypeActeurServiceBean extends GenericServiceBean<TypeActeur, Integer> implements TypeActeurServiceBeanLocal {

    @EJB
    private TypeActeurDaoBean dao;

    @Override
    protected GenericDAOBean<TypeActeur, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(TypeActeur e) {
        return e.getId();
    }

    @Override
    public Optional<TypeActeur> findByLibelle(String libelle) {
        List<TypeActeur> typeActeurs = this.dao.getAll(FilterParams.from(FilterParam.from("libelle", libelle)));
        if (!typeActeurs.isEmpty()) {
            return Optional.of(typeActeurs.get(0));
        }
        return Optional.empty();
    }
}
