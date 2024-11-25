package com.mediasoftstage.biblio.dao;

import com.mediasoftstage.biblio.entities.Emprunteur;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */

@Stateless
public class EmprunteurDaoBean extends GenericDAOBean<Emprunteur, Integer> {

    public EmprunteurDaoBean() {
        super(Emprunteur.class);
    }
}
