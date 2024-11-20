/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.TraceBlocageDaoBean;
import com.mediasofthome.biblio.entities.CompteEpargne;
import com.mediasofthome.biblio.entities.TraceBlocage;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author TOKPE Kossi Voltaire
 */
@Stateless
public class TraceBlocageServiceBean extends GenericServiceBean<TraceBlocage, Long>
        implements TraceBlocageServiceBeanLocal{
    @EJB
    private TraceBlocageDaoBean dao;
    @Override
    protected GenericDAOBean<TraceBlocage, Long> getDAO() {
        return this.dao;
    }
    @Override
    public Long getId(TraceBlocage e) {
        return e.getId();
    }
    @Override
    public TraceBlocage creerTraceBlocage(CompteEpargne cEp,LocalDate dateOperation,BigDecimal montantBloque,BigDecimal montantDebloque,
            String remarque) {
        TraceBlocage tb = new TraceBlocage();
        tb.setCompteEpargne(cEp);
        tb.setDateOperation(dateOperation);
        tb.setMontantBloque(montantBloque);
        tb.setMontantLibere(montantDebloque);
        tb.setRemarque(remarque);
        this.addOne(tb);
        return tb;
    }
    @Override
    public  TraceBlocage modifierTraceBlocage(TraceBlocage tb, CompteEpargne cEp, LocalDate dateOperation,
                 BigDecimal montantBloque,BigDecimal montantDebloque,String remarque){
        tb.setCompteEpargne(cEp);
        tb.setDateOperation(dateOperation);
        tb.setMontantBloque(montantBloque);
        tb.setMontantLibere(montantDebloque);
        tb.setRemarque(remarque);
        this.updateOne(tb);
        return tb;
    }
    @Override
    public List<TraceBlocage> getAllByCompteEpargne(Long idCompte) {
      return this.dao.getAllByCompteEpargne(idCompte);
    }
}
