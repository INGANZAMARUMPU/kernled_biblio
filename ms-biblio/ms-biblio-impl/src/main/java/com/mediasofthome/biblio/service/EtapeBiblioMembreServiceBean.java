package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EtapeBiblioMembreDaoBean;
import com.mediasofthome.biblio.entities.EtapeBiblioMembre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi, 19 juin 2023 à 14:48
 */
@Stateless
public class EtapeBiblioMembreServiceBean extends GenericServiceBean<EtapeBiblioMembre, Long>
        implements EtapeBiblioMembreServiceBeanLocal {

    @EJB
    private EtapeBiblioMembreDaoBean dao;

    @Override
    protected GenericDAOBean<EtapeBiblioMembre, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(EtapeBiblioMembre e) {
        return e.getId();
    }

}
