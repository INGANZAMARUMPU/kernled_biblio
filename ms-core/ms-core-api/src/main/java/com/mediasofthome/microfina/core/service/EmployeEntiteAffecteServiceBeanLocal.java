/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.core.service;

import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.params.FilterParams;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.biblio.core.entities.Employe;
import com.mediasofthome.biblio.core.entities.EmployeEntiteAffecte;

import java.util.List;

/**
 *
 * @author mawuli
 */
public interface EmployeEntiteAffecteServiceBeanLocal extends GenericServiceBeanLocal<EmployeEntiteAffecte, Long> {

    /**
     * Permet la récupération des entités affectées à un utilisateur donné
     *
     * @param employe
     * @return une liste d'entités
     */
    List<KEntity> chargerEntiteParEmploye(Employe employe);

    /**
     * Permet la récupération des entités affectées à un utilisateur donné
     *
     * @param filterParams
     * @return une liste d'entités
     */
    List<KEntity> chargerEntites(FilterParams filterParams);
}
