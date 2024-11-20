/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.dao;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.biblio.core.entities.TypeOperation;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class TypeOperationDaoBean extends GenericDAOBean<TypeOperation, String> {
    
    public TypeOperationDaoBean() {
        super(TypeOperation.class);
    }
    
}
