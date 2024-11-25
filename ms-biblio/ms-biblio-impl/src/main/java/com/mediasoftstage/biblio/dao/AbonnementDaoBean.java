package com.mediasoftstage.biblio.dao;

import com.mediasoftstage.biblio.entities.Abonnement;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */

@Stateless
public class AbonnementDaoBean extends GenericDAOBean<Abonnement, Integer> {

    public AbonnementDaoBean() {
        super(Abonnement.class);
    }
}
