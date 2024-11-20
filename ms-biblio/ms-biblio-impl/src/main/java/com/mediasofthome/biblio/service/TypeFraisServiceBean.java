/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypeFraisDaoBean;
import com.mediasofthome.biblio.entities.Frais;
import com.mediasofthome.biblio.entities.TypeFrais;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.entities.TypeOperation;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author sama
 */
@Stateless
public class TypeFraisServiceBean extends GenericServiceBean<TypeFrais, Long> implements TypeFraisServiceBeanLocal {

    @EJB
    private TypeFraisDaoBean dao;

    @Override
    protected GenericDAOBean<TypeFrais, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TypeFrais e) {
        return e.getId();
    }

    @Override
    public List<TypeFrais> onTypeOperationChange(Frais frais, TypeOperation typeOperation) {
        if (Objects.nonNull(frais.getEntity()) && Objects.nonNull(typeOperation)) {
            return this.getAll(FilterParams.from(FilterParam.from("typeOperation", typeOperation), FilterParam.from("entity", frais.getEntity())));
        }
        return null;
    }
}
