package com.mediasoftstage.biblio.dao;

import com.mediasoftstage.biblio.entities.Livre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */

@Stateless
public class LivreDaoBean extends GenericDAOBean<Livre, Integer> {

    public LivreDaoBean() {
        super(Livre.class);
    }
}
