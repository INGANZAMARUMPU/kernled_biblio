/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TypePieceTypeMembreDaoBean;
import com.mediasofthome.biblio.entities.TypeMembre;
import com.mediasofthome.biblio.entities.TypePiece;
import com.mediasofthome.biblio.entities.TypePieceTypeMembre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.params.FilterParam;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

/**
 *
 * @author Mediasoft
 */
@Stateless
public class TypePieceTypeMembreServiceBean extends GenericServiceBean<TypePieceTypeMembre, Long> implements TypePieceTypeMembreBeanLocal {

    @EJB
    private TypePieceTypeMembreDaoBean dao;

    @Override
    protected GenericDAOBean<TypePieceTypeMembre, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(TypePieceTypeMembre e) {
        return e.getId();
    }

    @Override
    public List<TypePiece> chargerTypePieceParTypeMembre(TypeMembre typeMembre) {
        return this.dao.chargerTypePieceParTypeMembre(typeMembre);
    }

    @Override
    public TypePieceTypeMembre selectionner(TypeMembre tpMembre, TypePiece tpPiece, KEntity entite) {
        List<TypePieceTypeMembre> tpPieceMembres = dao.getAll(FilterParams.from(
                FilterParam.from("typeMembre", tpMembre),
                FilterParam.from("typePiece", tpPiece),
                FilterParam.from("entity", entite)));
        if (tpPieceMembres.size() == 1) {
            return tpPieceMembres.get(0);
        }
        return null;
    }
}
