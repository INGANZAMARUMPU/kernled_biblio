/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ModeleNumeroMembreDaoBean;
import com.mediasofthome.biblio.entities.ModeleNumeroMembre;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.omnifaces.util.Messages;

/**
 *
 * @author Mediasoft
 */
@Stateless
public class ModeleNumeroMembreServiceBean extends GenericServiceBean<ModeleNumeroMembre, Long> implements ModeleNumeroMembreServiceBeanLocal {

    private static final String characters = "ABCDEFGHIJKMNOPRSTUVWXYabcdefghijklmnopqrstuvwxyz!@#$%&*()_+=:;|,./?><";

    @EJB
    private ModeleNumeroMembreDaoBean dao;

    @Override
    protected GenericDAOBean<ModeleNumeroMembre, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(ModeleNumeroMembre e) {
        return e.getId();
    }

    public String verificationDuModeleEcriture(String model) {
        for (int i = 0; i < model.length(); i++) {
            for (int j = 0; j < characters.length(); j++) {
                System.err.println("i value : " + model.charAt(i) + " j value : " + characters.charAt(j));
                if (model.charAt(i) == characters.charAt(j)) {
                    Messages.addFlashGlobalError("Attention : Le charactère '" + model.charAt(i) + "' " + "ne peu être utilisé dans la génération de ce modèle");
                    return null;
                }
            }
        }
        if (model.length() > 28) {
            Messages.addFlashGlobalError("Format invalide : Vérifier la longueur de votre mofèle");
            return null;
        } else if (model.length() < 28) {
            Messages.addFlashGlobalError("Format invalide : Vérifier la longueur de votre mofèle");
            return null;
        }
        if (!Character.isDigit(model.charAt(4))) {
            Messages.addFlashGlobalError(model.charAt(9) + " doit être un chiffre");
            return null;
        }
        if (!Character.isDigit(model.charAt(11))) {
            Messages.addFlashGlobalError(model.charAt(9) + " doit être un chiffre");
            return null;
        }
        if (!Character.isDigit(model.charAt(18))) {
            Messages.addFlashGlobalError(model.charAt(9) + " doit être un chiffre");
            return null;
        }
        if (!Character.isDigit(model.charAt(25))) {
            Messages.addFlashGlobalError(model.charAt(9) + " doit être un chiffre");
            return null;
        }
        if (model.charAt(0) != '['
                || model.charAt(1) != '['
                || model.charAt(7) != '['
                || model.charAt(8) != '['
                || model.charAt(14) != '['
                || model.charAt(15) != '['
                || model.charAt(21) != '['
                || model.charAt(22) != '[') {
            Messages.addFlashGlobalError("Modèle incorect ! Un '[' est mal renseign2");
            return null;
        } else if (model.charAt(5) != ']'
                || model.charAt(6) != ']'
                || model.charAt(12) != ']'
                || model.charAt(13) != ']'
                || model.charAt(19) != ']'
                || model.charAt(20) != ']'
                || model.charAt(26) != ']'
                || model.charAt(27) != ']') {
            Messages.addFlashGlobalError("Modèle incorect ! Un ']' est mal renseign2");
            return null;
        }
        if (model.charAt(2) == model.charAt(9)) {
            Messages.addFlashGlobalError("Dupplication de la lettre " + model.charAt(9));
            return null;
        } else if (model.charAt(2) == model.charAt(16)) {
            Messages.addFlashGlobalError("Dupplication de la lettre 2 " + model.charAt(16));
            return null;
        } else if (model.charAt(2) == model.charAt(23)) {
            Messages.addFlashGlobalError("Dupplication de la lettre 3 " + model.charAt(23));
            return null;
        } else if (model.charAt(9) == model.charAt(16)) {
            Messages.addFlashGlobalError("Dupplication de la lettre 4 " + model.charAt(16));
            return null;
        } else if (model.charAt(9) == model.charAt(23)) {
            Messages.addFlashGlobalError("Dupplication de la lettre 5 " + model.charAt(23));
            return null;
        } else if (model.charAt(16) == model.charAt(23)) {
            Messages.addFlashGlobalError("Dupplication de la lettre 6 " + model.charAt(23));
            return null;
        }
        return null;
    }
}
