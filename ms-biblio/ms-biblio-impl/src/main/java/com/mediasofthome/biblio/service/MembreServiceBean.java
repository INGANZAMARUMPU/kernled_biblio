/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.MembreDaoBean;
import com.mediasofthome.biblio.domain.MembreDTO;
import com.mediasofthome.biblio.domain.MembreStats;
import com.mediasofthome.biblio.domain.MembreVo;
import com.mediasofthome.biblio.entities.Membre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

/**
 *
 * @author mawuli
 */
@Stateless
public class MembreServiceBean extends GenericServiceBean<Membre, Long> implements MembreServiceBeanLocal {

    @EJB
    private MembreDaoBean dao;

    @Override
    protected GenericDAOBean<Membre, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Membre e) {
        return e.getId();
    }

    @Override
    public Membre getOne(Long id) {
        Membre membre = this.dao.getOne(id);
        membre.getNationalite();
        membre.getAdresse();
        membre.getCategorie();
        membre.getPartSociale();
        membre.getSousTypeMembre();
        membre.getDomaineActivite();
        membre.getEntity();
        return membre;
    }

    @Override
    public List<MembreVo> selectionnerVo() {
        return dao.selectionnerVo();
    }

    @Override
    public List<MembreVo> membresVo() {
        return dao.membresVo();
    }

    @Override
    public List<MembreVo> selectionnerVoParEntite(Integer idAgence) {
        return dao.selectionnerVoParEntite(idAgence);
    }

    @Override
    public List<MembreDTO> lastFourMembreNonVise() {
        return this.dao.lastFourthMembreNonVise();
    }

    @Override
    public List<MembreVo> chargerMembreVo(Entite entite) {
        return this.dao.chargerMembreVo(entite);
    }

    @Override
    public List<MembreVo> chargerMembreVoParParent(Entite entite) {
        return dao.chargerMembreVoParParent(entite);
    }
    
    @Override
    public List<MembreVo> chargerMembreVoParParent(Entite entite, String filter){
        return dao.chargerMembreVoParParent(entite, filter);
    }

    @Override
    public List<MembreVo> chargerMembreAttenteVo(Entite entite) {
        return this.dao.chargerMembreAttenteVo(entite);
    }

    @Override
    public MembreStats chargerMembreStats(Entite entite, boolean vise) {
        return this.dao.getMembreStats(entite, vise);
    }

    @Override
    public MembreVo chargerMembreVoById(Long id) {
        return dao.chargerMembreVoById(id);
    }

    @Override
    public List<Membre> filterByNumeroMembre(String prefixe, Entite entite) {
        return this.dao.filterByNumeroMembre(prefixe, entite);
    }

    @Override
    public List<Membre> filterMembreByNumber(String prefixe, KEntity entite) {
        return this.dao.filterMembreByNumber(prefixe, entite);
    }

    @Override
    public List<MembreVo> chargerMembreVo(Entite entite, String filter) {
        return this.dao.chargerMembreVo(entite, filter);
    }
    
    @Override
    public List<MembreVo> chargementDeMembreVo(Entite entite, String filter) {
        return this.dao.chargementDeMembreVo(entite, filter);
    }
    
}
