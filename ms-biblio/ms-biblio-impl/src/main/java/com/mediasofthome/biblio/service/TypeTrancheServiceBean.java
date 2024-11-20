/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypeTrancheDaoBean;
import com.mediasofthome.biblio.entities.TypeTranche;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author believe
 */
@Stateless
public class TypeTrancheServiceBean extends GenericServiceBean<TypeTranche, String>
        implements TypeTrancheServiceBeanLocal {

    @EJB
    private TypeTrancheDaoBean dao;

    @Override
    protected GenericDAOBean<TypeTranche, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(TypeTranche e) {
        return e.getCode();
    }

}
