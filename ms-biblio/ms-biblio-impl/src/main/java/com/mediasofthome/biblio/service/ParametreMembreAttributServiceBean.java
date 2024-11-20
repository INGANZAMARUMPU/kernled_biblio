package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ParametreMembreAttributDaoBean;
import com.mediasofthome.biblio.entities.MembreAttribut;
import com.mediasofthome.biblio.entities.ParametreMembreAttribut;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SAMIE Pyabalo
 * @since 32 octobre 2023 à 16:05
 */
@Stateless
public class ParametreMembreAttributServiceBean extends GenericServiceBean<ParametreMembreAttribut, Long>
        implements ParametreMembreAttributServiceBeanLocal {

    @EJB
    private ParametreMembreAttributDaoBean dao;

    @EJB
    private MembreAttributServiceBeanLocal mapService;

    @Override
    protected GenericDAOBean<ParametreMembreAttribut, Long> getDAO() {
        return dao;
    }

    @Override
    public Long getId(ParametreMembreAttribut e) {
        return e.getId();
    }

    @Override
    public List<ParametreMembreAttribut> initListParams(ParametreMembreAttribut param, Entite entite) {
        List<ParametreMembreAttribut> params = new ArrayList<>();
        List<MembreAttribut> membreAttributParams = this.mapService.getAll();
        for (MembreAttribut map : membreAttributParams) {
            param = new ParametreMembreAttribut(map.isVisible(), false, map.isRequis(), " ", entite, map);
            params.add(param);
            param = new ParametreMembreAttribut();
        }
        return params;
    }

    @Override
    public void addParams(List<ParametreMembreAttribut> params) {
        for (ParametreMembreAttribut ap : params) {
            this.addOneWithFlush(ap);
        }
    }

    @Override
    public ParametreMembreAttribut updateParams(List<ParametreMembreAttribut> params) {
        ParametreMembreAttribut pma = new ParametreMembreAttribut();
        for (ParametreMembreAttribut ap : params) {
            pma = this.updateOne(ap);
        }
        return pma;
    }
}
