/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.GuichetDaoBean;
import com.mediasofthome.biblio.entities.Guichet;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class GuichetServiceBean extends GenericServiceBean<Guichet, String> implements GuichetServiceBeanLocal {

    @EJB
    private GuichetDaoBean dao;

    @Override
    protected GenericDAOBean<Guichet, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Guichet e) {
        return e.getCode();
    }

}
