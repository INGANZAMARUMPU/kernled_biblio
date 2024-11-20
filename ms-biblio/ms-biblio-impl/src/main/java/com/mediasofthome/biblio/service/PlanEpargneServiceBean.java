/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.PlanEpargneDaoBean;
import com.mediasofthome.biblio.domain.CompteEpargneVo;
import com.mediasofthome.biblio.entities.PlanEpargne;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.Objects;

/**
 *
 * @author mawuli
 */
@Stateless
public class PlanEpargneServiceBean extends GenericServiceBean<PlanEpargne, Long> implements PlanEpargneServiceBeanLocal {

    @EJB
    private PlanEpargneDaoBean dao;
    @EJB
    private CompteEpargneServiceBeanLocal compteService;

    @Override
    protected GenericDAOBean<PlanEpargne, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(PlanEpargne e) {
        return e.getId();
    }

    @Override
    public PlanEpargne selectionnerUnParId(Long id) {
        return this.dao.selectionnerUnParId(id);
    }

    @Override
    public PlanEpargne validerPlanEpargne(PlanEpargne planEpargne) {
        CompteEpargneVo compte = compteService.chargerCompteVoParId(planEpargne.getCompteEpargne().getId());
        if (Objects.nonNull(compte)) {
            if (compte.getSolde().doubleValue() > planEpargne.getMontantPeriodique().doubleValue()) {

            }
        }
        return planEpargne;
    }

}
