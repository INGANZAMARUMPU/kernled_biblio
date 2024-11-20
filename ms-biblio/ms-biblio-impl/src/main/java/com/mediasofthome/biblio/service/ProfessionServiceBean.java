/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProfessionDaoBean;
import com.mediasofthome.biblio.entities.Profession;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class ProfessionServiceBean extends GenericServiceBean<Profession, Integer> implements ProfessionServiceBeanLocal {

    @EJB
    private ProfessionDaoBean dao;

    @Override
    protected GenericDAOBean<Profession, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(Profession e) {
        return e.getId();
    }

}
