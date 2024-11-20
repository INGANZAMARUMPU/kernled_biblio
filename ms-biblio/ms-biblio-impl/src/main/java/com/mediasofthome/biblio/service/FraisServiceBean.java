/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.FraisDaoBean;
import com.mediasofthome.biblio.entities.CritereFrais;
import com.mediasofthome.biblio.entities.Frais;
import com.mediasofthome.biblio.entities.Membre;
import com.mediasofthome.biblio.entities.TypeFrais;
import com.mediasofthome.biblio.utils.CritereFraisContext;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.entities.TypeOperation;
import com.mediasofthome.biblio.core.service.TypeOperationServiceBeanLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mawuli
 */
@Stateless
public class FraisServiceBean extends GenericServiceBean<Frais, Long> implements FraisServiceBeanLocal {

    @EJB
    private FraisDaoBean dao;
    @EJB
    protected TypeOperationServiceBeanLocal typeOperationService;
    @EJB
    private CritereFraisServiceBeanLocal critereFraisService;

    @Override
    protected GenericDAOBean<Frais, Long> getDAO() {
        return this.dao;
    }

    @Override
    public List<Frais> getFraisApplicable(Membre membre) {
        List<Frais> fraiss = this.getAllWithPriorite(FilterParams.
                from("typeFrais.typeOperation", typeOperationService.getOne(TypeOperation.CODE_BIBLIO)));
        List<Frais> result = new ArrayList<>();
        for (Frais f : fraiss) {
            if (!this.veriferFrais(f, membre)) {
                result.add(f);
            }
        }

        return result;
    }

    private boolean veriferFrais(Frais frais, Membre membre) {
        boolean result = Boolean.FALSE;
        CritereFraisContext critereContext = new CritereFraisContext().build(membre);
        List<CritereFrais> critereFrais = this.critereFraisService.getAll(FilterParams.from("frais", frais));
        Collections.reverse(critereFrais);
        critereFrais = this.contruireChaine(critereFrais);
        for (CritereFrais e : critereFrais) {
            result = e.proceder(critereContext);
        }
        return result;
    }

    private List<CritereFrais> contruireChaine(List<CritereFrais> list) {
        CritereFrais critereFrais = null;
        for (CritereFrais c : list) {
            c.setSuivant(critereFrais);
            critereFrais = c;
        }
        return list;
    }

    @Override
    public List<Frais> getAllWithPriorite(FilterParams filterParams) {
        return this.dao.getAllWithPriorite(filterParams);
    }

    @Override
    public Long getId(Frais e) {
        return e.getId();
    }

    @Override
    public List<Frais> getAll() {
        return this.dao.getAll();
    }

    @Override
    public List<Frais> recupererParTypeFrais(TypeFrais typeFrais) {
        return this.getAll(FilterParams.from(FilterParam.from("typeFrais", typeFrais)));
    }

}
