/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.PeriodeDaoBean;
import com.mediasofthome.biblio.entities.Periode;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author TOKPE Kossi Voltaire
 * Cette classe contient les services du paramétrage des unités de périodicités
 */
@Stateless
public class PeriodeServiceBean  extends GenericServiceBean<Periode, Integer>
        implements PeriodeServiceBeanLocal{
    @EJB
    private PeriodeDaoBean dao;

    @Override
    protected GenericDAOBean<Periode, Integer> getDAO() {
        return dao;
    }
    @Override
    public Integer getId(Periode e) {
       return e.getId();
    }
    
    
}
