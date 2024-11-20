/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.GroupeSolidaireDaoBean;
import com.mediasofthome.biblio.entities.GroupeSolidaire;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMA
 */
@Stateless
public class GroupeSolidaireServiceBean extends GenericServiceBean<GroupeSolidaire, Long> implements GroupeSolidaireServiceBeanLocal{
    
    @EJB
    private GroupeSolidaireDaoBean dao;

    @Override
    protected GenericDAOBean<GroupeSolidaire, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(GroupeSolidaire e) {
        return e.getId();
    }
    
}
