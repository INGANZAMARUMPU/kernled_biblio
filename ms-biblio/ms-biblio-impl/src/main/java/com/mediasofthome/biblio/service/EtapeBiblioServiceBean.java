/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.EtapeBiblioDaoBean;
import com.mediasofthome.biblio.entities.EtapeBiblio;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 *
 * @author TOKPE
 */
@Stateless
public class EtapeBiblioServiceBean extends GenericServiceBean<EtapeBiblio, Long>
        implements EtapeBiblioServiceBeanLocal {

    @EJB
    private EtapeBiblioDaoBean dao;

    @Override
    protected GenericDAOBean<EtapeBiblio, Long> getDAO() {
        return this.dao;
    }

    @Override
    public EtapeBiblio getOne(FilterParams filterParams) {
        return this.dao.getOne(filterParams);
    }

    @Override
    public Long getId(EtapeBiblio e) {
        return e.getId();
    }

    @Override
    public List<EtapeBiblio> getByCurrentUser(Entite parent) {
        return this.dao.getAll(FilterParams.from(
                "processusBiblio.entite", parent));
    }

    @Override
    public List<EtapeBiblio> sortedList(List<EtapeBiblio> list) {
        return list.
                stream()
                .sorted(Comparator.comparingInt(EtapeBiblio::getOrdre))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isOrdreNotExist(List<EtapeBiblio> etapes, int ordre) {
        Long countIn = etapes
                .stream()
                .filter(e -> e.getOrdre() == ordre)
                .count();
        return countIn == 0;
    }

    @Override
    public EtapeBiblio findLastEtapeByOrdre(List<EtapeBiblio> l) throws Exception {
        try {
            return l
                    .stream()
                    .max(Comparator.comparingInt(EtapeBiblio::getOrdre))
                    .orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
}
