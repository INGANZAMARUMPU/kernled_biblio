/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.entities.Acteur;
import com.mediasofthome.biblio.entities.Procuration;
import com.mediasofthome.biblio.entities.TypeActeur;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;

import java.util.List;

/**
 *
 * @author mawuli
 */
public interface ActeurServiceBeanLocal extends GenericServiceBeanLocal<Acteur, Long> {

    Acteur getOne(Long id, String... attributs);

    List<Acteur> getAll(String... attributs);

    Acteur getById(Long id);

    List<Acteur> selectionnerTout();

    List<Acteur> selectionnerTout(Long idMembre);

    /**
     *
     * @param prefixe
     * @param typeActeur
     * @return
     */
    List<Acteur> getMandataires(String prefixe, TypeActeur typeActeur);

    /**
     *
     * @param procuration
     * @param ta
     */
    void ajouterAvecPopUp(Procuration procuration, TypeActeur ta);

    /**
     *
     * @param acteur
     */
    void validateur(Acteur acteur);
}
