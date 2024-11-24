package com.mediasofthome.biblio.bean;

import com.mediasofthome.biblio.constants.BiblioConstants;
import com.mediasofthome.krnl.constants.CoreMessageConstants;
import com.mediasofthome.krnl.exception.BusinessException;
import com.mediasofthome.krnl.web.beans.AbstractVariableBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;
import java.util.logging.Level;
import org.omnifaces.util.Messages;

/**
 *
 * @author INGANZAMARUMPU
 */
@Named
@RequestScoped
public class BiblioVaribaleBean extends AbstractVariableBean {

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void save() {
        try {
            this.service.updateAll(this.list);
            Messages.addFlashGlobalInfo(CoreMessageConstants.UPDATE_SUCCEEDED);
        } catch (BusinessException e) {
            Messages.addFlashGlobalError(e.getMessage());
        } catch (Exception e) {
            Messages.addFlashGlobalError(CoreMessageConstants.UNKNOWN_ERROR);
            this.LOGGER.log(Level.SEVERE, CoreMessageConstants.UNKNOWN_ERROR, e);
        }
    }

    @Override
    protected void variableCodes(List<String> codes) {
        codes.add(BiblioConstants.VAR_BIBLIO_MEMBRE_MODELE_NUMERO);
    }
}
