package com.mediasoftstage.biblio.dao;

import com.mediasoftstage.biblio.entities.Exemplaire;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */

@Stateless
public class ExemplaireDaoBean extends GenericDAOBean<Exemplaire, Integer> {

    public ExemplaireDaoBean() {
        super(Exemplaire.class);
    }
}
