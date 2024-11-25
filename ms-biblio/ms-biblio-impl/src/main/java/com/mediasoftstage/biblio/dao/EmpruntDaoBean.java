package com.mediasoftstage.biblio.dao;

import com.mediasoftstage.biblio.entities.Emprunt;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */

@Stateless
public class EmpruntDaoBean extends GenericDAOBean<Emprunt, Integer> {

    public EmpruntDaoBean() {
        super(Emprunt.class);
    }
}
