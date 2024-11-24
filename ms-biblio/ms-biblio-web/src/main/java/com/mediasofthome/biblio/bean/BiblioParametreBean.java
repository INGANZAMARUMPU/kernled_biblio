/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasofthome.krnl.constants.CorePermissionConstants;
import com.mediasofthome.krnl.service.UserServiceBeanLocal;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author INGANZAMARUMPU
 */
@Named
@ViewScoped
public class BiblioParametreBean implements Serializable {

    @EJB
    private UserServiceBeanLocal userService;

    public boolean peutAccederGeneral() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_GENERAL);
    }

    public boolean peutAccederTypeGroupement() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_TYPE_DOCUMENT_LIST);
    }

    public boolean peutAccederTypePiecesTypeMembre() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_TYPE_PIECE_TYPE_MEMBRE_EDITE);
    }

    public boolean peutAccederAuxSituationMatrimoniales() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_DONNEE_DE_REFERENCE_SITUATION_MATRIMONIALE_ACCES);

    }

    public boolean peutAccederAuxTypeFiliations() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_DONNEE_DE_REFERENCE_TYPE_FILIATION_ACCES);

    }

    public boolean peutAccederAuxTypePieces() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_TYPE_PIECE_ACCES);

    }

    public boolean peutAccederAuxTypeMembres() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_TYPE_MEMBRE_ACCES);

    }

    public boolean peutAccederAuxCategorieMembres() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CATEGORIE_MEMBRE_ACCES);

    }

    public boolean peutAccederAuxPosts() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_POST_ACCES);

    }

    public boolean peutAccederAuxProfessions() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_PROFESSION_ACCES);

    }

    public boolean peutAccederAuxSecteurActivites() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_SECTEUR_ACTIVITE_ACCES);

    }

    public boolean peutAccederAuxBrancheActivites() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_BRANCHE_ACTIVITE_ACCES);

    }

    public boolean peutAccederAuxDomaineActivites() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_DOMAINE_ACTIVITE_ACCES);

    }

    public boolean peutAccederAuxTypeProcurations() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_TYPE_PROCURATION_ACCES);

    }

    public boolean peutAccederAuxCategoriesPartSociales() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CATEGORIE_PART_SOCIALE_ACCES);

    }

    public boolean peutAccederAuxMotifs() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_MOTIF_ACCES);

    }

    public boolean peutAccederAuxCivilites() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_PARAMETRE_CIVILITE_ACCES);

    }

    public boolean peutAccederAuxSexes() {
        return this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_DONNE_DE_REFERENCE_SEXE_ACCES);

    }
    
    public boolean peutAccederAuxFonctions() {
        return this.userService.isPermitted(CorePermissionConstants.PERM_FONCTION_LIST);
    }

}
