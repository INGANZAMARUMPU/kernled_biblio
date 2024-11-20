/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.core.service;

import com.mediasofthome.krnl.entities.*;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.biblio.core.entities.Entite;
import com.mediasofthome.biblio.core.entities.EntiteSettings;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.TreeNode;

import java.util.List;

/**
 *
 * @author mawuli
 */
public interface EntiteServiceBeanLocal extends GenericServiceBeanLocal<Entite, Integer> {

    /**
     * Recupere les entités autorisé pour un utilisateur donné
     *
     * @param user
     *
     * @return
     */
    List<KEntity> recupererEntiteAutorise(User user);

    /**
     *
     * @return
     */
    TreeNode<Entite> creerArbre();

    /**
     *
     * @return
     */
    TreeNode<KEntity> creerArbreWithKentity();

    /**
     *
     * @param entite
     * @param user
     * @return
     */
    TreeNode<KEntity> initRacine(KEntity entite, User user);

    /**
     *
     * @return
     */
    List<Entite> selectionnerTout();

    /**
     *
     * @param entite
     * @return
     */
    boolean root(Entite entite);

    /**
     *
     * @param entityType Le Type d'entité
     * @return
     */
    boolean canBeRoot(EntityType entityType);

    /**
     *
     * @param parent
     * @param entityType
     * @return
     */
    List<Entite> entitesByPerson(Entite parent, EntityType entityType);

    /**
     *
     * @param telephone2
     * @param indicatif2
     * @return
     */
    String telephone2(String telephone2, Integer indicatif2);

    /**
     *
     * @param b
     * @return
     */
    List<EntityType> typeEntiteList(Boolean b);

    List<EntityType> typeEntiteList();

    /**
     *
     * @param parent
     * @param bool
     * @return
     */
    List<Entite> initAgenceList(Entite parent, boolean bool);

    /**
     *
     * @param entite
     * @return
     */
    List<Entite> chargerAgences(Entite entite);

    /**
     *
     * @param entite
     * @return
     */
    List<Entite> chargerAgences(Entite... entite);

    /**
     *
     * @param e
     * @return
     */
    List<Entite> entiteParents(Entite e);

    /**
     *
     * @param kentity
     * @return
     */
    KEntity getParent(KEntity kentity);

    /**
     *
     * @param event
     */
    void rolesSelected(UnselectEvent event);

    String verifierChampsAddressEntiteEtAjouter(Entite entite, Integer idEntite, Integer entityId, Person person, boolean linkedUser,
            String telephone1, Integer indicatif1, String telephone2, Integer indicatif2, String userName, User user, EntityVariable entityVariable,
            Variable variable, String nomUtilisateur, String motDePasseAleatoire, String REQUIRED_ADDRESS, String REQUIRED_LOCALITY,
            String REQUIRED_LOCALITY_TYPE, String REQUIRED_COUNTRY, String LIST_OUTCOME,EntiteSettings entiteSettings);

}
