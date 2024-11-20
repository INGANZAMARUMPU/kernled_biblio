package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EtapeDaoBean;
import com.mediasofthome.biblio.entities.Etape;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author SAMIE Pyabalo
 * @since Lundi, 19 juin 2023 à 12:18
 */
@Stateless
public class EtapeServiceBean extends GenericServiceBean<Etape, Long> 
        implements EtapeServiceBeanLocal{

    @EJB
    private EtapeDaoBean dao;

    
    @Override
    protected GenericDAOBean<Etape, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Etape e) {
return e.getId();
    }

}
