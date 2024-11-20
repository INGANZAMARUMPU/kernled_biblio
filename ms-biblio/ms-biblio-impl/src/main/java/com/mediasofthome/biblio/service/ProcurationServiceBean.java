/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.service;

import com.mediasofthome.biblio.dao.ProcurationDaoBean;
import com.mediasofthome.biblio.entities.Procuration;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import static com.mediasofthome.krnl.service.GenericServiceBean.LOGGER;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.time.LocalDate;
import java.util.logging.Level;
import org.omnifaces.util.Messages;

/**
 *
 * @author mawuli
 */
@Stateless
public class ProcurationServiceBean extends GenericServiceBean<Procuration, Long> implements ProcurationServiceBeanLocal {

    @EJB
    private ProcurationDaoBean dao;

    @Override
    protected GenericDAOBean<Procuration, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Procuration e) {
        return e.getId();
    }

    @Override
    public void validateur(Procuration procuration) {
        if (procuration.getDateProcuration() == null) {
            return;
        }
        if (procuration.getDateProcuration().isAfter(LocalDate.now())) {
            Messages.addGlobalError("Veuillez saisir une date de procuration inférieure ou égale à la date d'aujourd'hui.");
            LOGGER.log(Level.SEVERE, "-> La date de procuration est erronée");
            return;
        }
        if (procuration.getEcheance() == null) {
            return;
        }
        if (!procuration.getEcheance().isAfter(procuration.getDateProcuration())) {
            Messages.addGlobalError("La date d'échéance doit être supérieure à la date d'aujourd'hui");
            LOGGER.log(Level.SEVERE, "-> L'échéance est erronée");
            return;
        }
        if (procuration.getDateProcuration().isAfter(procuration.getEcheance())) {
            Messages.addGlobalError("La date d'échéance doit être supérieure à la date d'aujourd'hui");
            LOGGER.log(Level.SEVERE, "-> La date d'échéance est erronée");
            return;
        }

        Messages.addGlobalInfo("La période entre la date de création de la procuration et l'échéance est valide");
    }
}
