/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypePieceDaoBean;
import com.mediasofthome.biblio.entities.TypePiece;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class TypePieceServiceBean extends GenericServiceBean<TypePiece, Integer> implements TypePieceServiceBeanLocal {

    @EJB
    private TypePieceDaoBean dao;

    @Override
    protected GenericDAOBean<TypePiece, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(TypePiece e) {
        return e.getId();
    }

}
