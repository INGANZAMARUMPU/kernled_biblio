/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.PosteDaoBean;
import com.mediasofthome.biblio.entities.Poste;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class PosteServiceBean extends GenericServiceBean<Poste, Integer> implements PosteServiceBeanLocal {

    @EJB
    protected PosteDaoBean posteDao;
    
    @Override
    protected GenericDAOBean<Poste, Integer> getDAO() {
        return this.posteDao;
    }

    @Override
    public Integer getId(Poste e) {
        return e.getId();
    }
    
}
