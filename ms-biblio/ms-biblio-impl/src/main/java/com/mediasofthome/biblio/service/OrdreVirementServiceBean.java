/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.OrdreVirementDaoBean;
import com.mediasofthome.biblio.entities.OrdreVirement;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class OrdreVirementServiceBean extends GenericServiceBean<OrdreVirement, Long> implements OrdreVirementServiceBeanLocal {

    @EJB
    private OrdreVirementDaoBean dao;

    @Override
    protected GenericDAOBean<OrdreVirement, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(OrdreVirement e) {
        return e.getId();
    }

    public OrdreVirement selectionnerUnParId(Long id) {
        return this.dao.selectionnerUnParId(id);
    }
}
