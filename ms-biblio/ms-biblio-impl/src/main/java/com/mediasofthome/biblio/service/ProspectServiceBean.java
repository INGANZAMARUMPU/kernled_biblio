/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProspectDaoBean;
import com.mediasofthome.biblio.entities.Prospect;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author admin
 */
@Stateless
public class ProspectServiceBean extends GenericServiceBean<Prospect, Long> implements ProspectServiceBeanLocal{
    @EJB
    private ProspectDaoBean dao;

    @Override
    protected GenericDAOBean<Prospect,Long> getDAO(){
      return this.dao;
    }

    @Override
    public Prospect getOne(Long id) {
        Prospect prospect = this.dao.getOne(id);
        prospect.getSensibilisateur();
        prospect.getCommercial();
        prospect.getSexe();
        prospect.getProfession();
        prospect.getCountry();
        prospect.getCity();
        prospect.getDistrict();
        return prospect;
    }
    
    
    @Override
    public Long getId(Prospect e) {
        return e.getId();
    }
    
}
