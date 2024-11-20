/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.entities.CritereEpargneTrancheDuree;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author Mediasoft
 */
@Stateless
public class CritereEpargneTrancheDureeDaoBean extends GenericDAOBean<CritereEpargneTrancheDuree, Long> {

    public CritereEpargneTrancheDureeDaoBean() {
        super(CritereEpargneTrancheDuree.class);
    }

}
