/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.PieceDaoBean;
import com.mediasofthome.biblio.entities.Piece;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author mawuli
 */
@Stateless
public class PieceServiceBean extends GenericServiceBean<Piece, Long> implements PieceServiceBeanLocal {

    @EJB
    private PieceDaoBean dao;

    @Override
    protected GenericDAOBean<Piece, Long> getDAO() {
        return dao;
    }

    @Override
    public Long getId(Piece e) {
        return e.getId();
    }

}
