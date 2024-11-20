/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.entities.Commercial;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author TOKPE Kossi Voltaire
 */
@Stateless
public class CommercialDaoBean extends GenericDAOBean<Commercial, Long>{

    public CommercialDaoBean() {
        super(Commercial.class);
    }
    
}
