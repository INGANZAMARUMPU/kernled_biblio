/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.dao;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.User;
import com.mediasofthome.biblio.core.entities.Employe;
import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 *
 * @author Mediasoft
 */
@Stateless
public class EmployeDaoBean extends GenericDAOBean<Employe, Long> {
    
    public List<Employe> getAll(String prefixe){
        String jpql = "SELECT DISTINCT e FROM Employe e "
                + "WHERE e.user IS NULL "
                + "AND (UPPER(e.lastName) LIKE UPPER(:prefixe) OR UPPER(e.firstName) LIKE UPPER(:prefixe))";
        TypedQuery<Employe> query = this.em.createQuery(jpql, Employe.class);
        query.setParameter("prefixe", "%" + prefixe + "%")
                .setParameter("prefixe", "%" + prefixe + "%");
        return query.getResultList();
    }
    
    public List<User> getAllUser(String prefixe){
        String jpql = "SELECT DISTINCT u FROM User u "
                + "WHERE (UPPER(u.username) LIKE UPPER(:prefixe))";
        TypedQuery<User> query = this.em.createQuery(jpql, User.class);
        query.setParameter("prefixe", "%" + prefixe + "%");
        return query.getResultList();
    }
    
    public EmployeDaoBean() {
        super(Employe.class);
    }
    
}
