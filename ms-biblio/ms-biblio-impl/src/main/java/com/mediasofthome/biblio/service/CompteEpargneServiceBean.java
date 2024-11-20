/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.CompteEpargneDaoBean;
import com.mediasofthome.biblio.domain.CompteEpargneVo;
import com.mediasofthome.biblio.domain.StatisticBiblioDTO;
import com.mediasofthome.biblio.entities.CompteEpargne;
import com.mediasofthome.biblio.entities.Membre;
import com.mediasofthome.biblio.entities.ProduitEpargne;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.biblio.core.entities.Entite;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author mawuli
 */
@Stateless
public class CompteEpargneServiceBean extends GenericServiceBean<CompteEpargne, Long> implements CompteEpargneServiceBeanLocal {

    @EJB
    private CompteEpargneDaoBean dao;

    @Override
    protected GenericDAOBean<CompteEpargne, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(CompteEpargne e) {
        return e.getId();
    }

    @Override
    public List<CompteEpargne> selectionnerTout() {
        return this.dao.selectionnerTout();
    }

    @Override
    public CompteEpargne getOne(Long id) {
        CompteEpargne compteEpargne = this.dao.getOne(id);
        compteEpargne.getProduitEpargne();
        return compteEpargne;
    }

    @Override
    public List<CompteEpargneVo> selectionnerVo() {
        return this.dao.selectionnerVo();
    }

    @Override
    public List<CompteEpargneVo> selectionnerVoParMembre(Long idMembre) {
        return this.dao.selectionnerVoParMembre(idMembre);
    }

    @Override
    public List<CompteEpargneVo> selectionnerVoParMembre(Long idMembre, Class<? extends ProduitEpargne> classe) {
        return this.dao.selectionnerVoParMembre(idMembre, classe);
    }

    @Override
    public List<CompteEpargneVo> selectionnerVoParMembreSans(Long idMembre, Class<? extends ProduitEpargne> classe) {
        return this.dao.selectionnerVoParMembreSans(idMembre, classe);
    }
    
    @Override
    public List<CompteEpargne> ensembleDeComptesFermer(){
        return this.dao.ensembleDeComptesFermer();
    }
    
    public List<CompteEpargne> filtrageDeCompteParEntiteConnecte(Entite e){
        return this.dao.filtrageDeCompteParEntiteConnecte(e);
    }
    
    @Override
    public List<StatisticBiblioDTO> getLastFiveAcount() {
        return this.dao.getLastFiveAcount();
    }

    @Override
    public String genererNumeroCompteEpargne(Membre membre, ProduitEpargne produit) {
        String numCompteEpargne = "";
        if (membre != null) {
            if (produit != null) {
                numCompteEpargne = RandomStringUtils.randomAlphanumeric(12).toUpperCase();
            } else {
                numCompteEpargne = RandomStringUtils.randomAlphanumeric(12).toUpperCase();
            }
        }
        return numCompteEpargne;
    }

    @Override
    public CompteEpargne creerCompte(Membre client, LocalDate dateOperation, ProduitEpargne produitEpargne, Entite agence) {
        String numCompteEpargne = genererNumeroCompteEpargne(client, produitEpargne);
        CompteEpargne compteEps = new CompteEpargne();
        compteEps.setNumeroCompte(numCompteEpargne);
        compteEps.setIntitule(produitEpargne.getLibelle());
        compteEps.setMembre(client);
        compteEps.setProduitEpargne(produitEpargne);
        compteEps.setAgence(agence);
        compteEps.setFerme(false);
        compteEps.setBloque(Boolean.FALSE);
        compteEps.setPrioritaire("N");
        this.addOne(compteEps);
        return compteEps;
    }

    @Override
    public List<CompteEpargneVo> chargerCompteVo(Entite entite) {
        return this.dao.chargerCompteVo(entite);
    }

    @Override
    public CompteEpargneVo chargerCompteVoParId(Long id) {
        return this.dao.chargerCompteVoParId(id);
    }

    @Override
    public List<CompteEpargneVo> chargerCompteVoParMembre(Long entite, String dtype) {
        return this.dao.chargerCompteVoParMembre(entite, dtype);
    }

    @Override
    public List<CompteEpargneVo> chargerCompteVoParMembreSans(Long entite, String dtype) {
        return this.dao.chargerCompteVoParMembreSans(entite, dtype);
    }

    @Override
    public boolean etatCompte(CompteEpargne c) {
        if (c.getBloque()) return true;
        return false;
    }

    @Override
    public boolean verifierSoldeDisponibleAvecMontantBloque(BigDecimal solde, BigDecimal montantBloque) {
        BigDecimal d = solde.subtract(montantBloque);
        return isValidMontant(d);
    }

    @Override
    public boolean verifierSoldeDisponibleAvecMontantDebloque(BigDecimal solde, BigDecimal montantDebloque) {
        BigDecimal d = solde.subtract(montantDebloque);
        return isValidMontant(d);
    }

    @Override
    public boolean etatSoldeDisponible(BigDecimal solde) {
        if (solde.compareTo(BigDecimal.ZERO) > 0) return true;
        return false;
    }

    @Override
    public boolean verifierDifferenceMontantBloqueEtDebloque(Long id, BigDecimal montantDebloque) {
        CompteEpargneVo stats=chargerCompteVoParId(id);
        BigDecimal d =  stats.getBloque().subtract(stats.getDebloque().add(montantDebloque));
        return isValidMontant(d);
    }

    @Override
    public boolean isValidMontant(BigDecimal montant) {
        if ((montant.compareTo(BigDecimal.ZERO) < 0) || (montant.compareTo(BigDecimal.ZERO) == 0)) return false;
        return true;
    }
    
    @Override
    public List<CompteEpargneVo> selectionnerVoParEntite(KEntity entite) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CompteEpargneVo> selectionnerVoParParent(KEntity parent) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CompteEpargneVo> chargerCompteVoParParent(Entite entite) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
