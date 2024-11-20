/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.service;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.User;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.dao.EmployeDaoBean;
import com.mediasofthome.biblio.core.entities.Employe;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.UnselectEvent;

import java.util.List;

/**
 *
 * @author Mediasoft
 */
@Stateless
public class EmployeServiceBean extends GenericServiceBean<Employe, Long> implements EmployeServiceBeanLocal{
    
    @EJB
    private EmployeDaoBean dao;

    @Override
    protected GenericDAOBean<Employe, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Employe e) {
        return e.getId();
    }
    
//    public List<Employe> getAll(String prefixe){
//        return this.dao.getAll(prefixe);
//    }
    
    @Override
    public List<User> getAllUser(String prefixe) {
        return this.dao.getAllUser(prefixe); 
    }
    
    @Override
    public void rolesSelected(UnselectEvent event) {
        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
