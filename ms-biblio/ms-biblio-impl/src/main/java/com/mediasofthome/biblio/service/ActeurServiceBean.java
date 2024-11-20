/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ActeurDaoBean;
import com.mediasofthome.biblio.entities.Acteur;
import com.mediasofthome.biblio.entities.Procuration;
import com.mediasofthome.biblio.entities.TypeActeur;
import com.mediasofthome.krnl.constants.CoreConstants;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.time.LocalDate;

import java.util.List;
import java.util.logging.Level;
import org.omnifaces.util.Messages;

/**
 *
 * @author mawuli
 */
@Stateless
public class ActeurServiceBean extends GenericServiceBean<Acteur, Long> implements ActeurServiceBeanLocal {

    @EJB
    private ActeurDaoBean dao;

    @Override
    protected GenericDAOBean<Acteur, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Acteur e) {
        return e.getId();
    }

    @Override
    public Acteur getOne(Long id, String... attributs) {
        return this.dao.getOne(id, attributs);
    }

    @Override
    public List<Acteur> getAll(String... attributs) {
        return this.dao.getAll(attributs);
    }

    @Override
    public Acteur getById(Long id) {
        return this.dao.getById(id);
    }

    @Override
    public List<Acteur> selectionnerTout() {
        return this.dao.selectionnerTout();
    }

    @Override
    public List<Acteur> selectionnerTout(Long idMembre) {
        return this.dao.selectionnerTout(idMembre);
    }

    @Override
    public List<Acteur> getMandataires(String prefixe, TypeActeur typeActeur) {
        return this.dao.getMandataires(prefixe, typeActeur);
    }

    @Override
    public void ajouterAvecPopUp(Procuration procuration, TypeActeur ta) {
        procuration.getMandataire().setTypeActeur(ta);
        procuration.setMandataire(this.addOneWithFlush(procuration.getMandataire()));
        Messages.addFlashGlobalInfo("Ajout effectué avec succès.");
        this.logService.info("Enregistrement de " + procuration.getMandataire().getClass().getSimpleName(), CoreConstants.LOG_CORE);
    }

    @Override
    public void validateur(Acteur acteur) {
        if (acteur.getPiece().getDateCreation() == null) {
            return;
        }
        if (acteur.getPiece().getDateCreation().isAfter(LocalDate.now())) {
            Messages.addGlobalError("Veuillez saisir une date de délivrance inférieure ou égale à la date d'aujourd'hui.");
            LOGGER.log(Level.SEVERE, "-> La date d'établissement est erronée");
            return;
        }
        if (acteur.getPiece().getDateExpiration() == null) {
            return;
        }
        if (!acteur.getPiece().getDateExpiration().isAfter(LocalDate.now())) {
            Messages.addGlobalError("Cette pièce n'est pas valide");
            LOGGER.log(Level.SEVERE, "-> La pièce est erronée");
            return;
        }
        if (acteur.getPiece().getDateCreation().isAfter(acteur.getPiece().getDateExpiration())) {
            Messages.addGlobalError("La date d'expiration doit être supérieure à la date de délivrance");
            LOGGER.log(Level.SEVERE, "-> La date est erronée");
            return;
        }

        Messages.addGlobalInfo("La pièce est valide");
    }
}
