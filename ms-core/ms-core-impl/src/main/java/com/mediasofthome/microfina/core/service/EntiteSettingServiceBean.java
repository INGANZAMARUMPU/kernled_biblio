/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.service;

import com.mediasofthome.krnl.constants.CoreConstants;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.AppSettings;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.entities.Settings;
import com.mediasofthome.krnl.service.AppSettingsServiceBeanRemote;
import com.mediasofthome.krnl.service.EntityVariableServiceBeanLocal;
import com.mediasofthome.krnl.service.GenericServiceBean;
import com.mediasofthome.krnl.service.SettingsServiceBeanRemote;
import com.mediasofthome.krnl.service.VariableServiceBeanLocal;
import com.mediasofthome.biblio.core.dao.EntiteSettingsDaoBean;
import com.mediasofthome.biblio.core.entities.EntiteSettings;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 *
 * @author Mediasoft
 */
@Stateless
public class EntiteSettingServiceBean extends GenericServiceBean<EntiteSettings, Long> implements EntiteSettingServiceBeanLocal {

    @EJB
    private EntiteSettingsDaoBean dao;
    @EJB
    private SettingsServiceBeanRemote settingsService;
    @EJB
    private SettingsServiceBeanRemote settingService;
    @EJB
    private EntityVariableServiceBeanLocal entityVariableService;
    @EJB
    private AppSettingsServiceBeanRemote appSettingsService;
    @EJB
    private VariableServiceBeanLocal variableService;

    @Override
    protected GenericDAOBean<EntiteSettings, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(EntiteSettings e) {
        return e.getId();
    }

    @Override
    public AppSettings getDefaultAppSetings(KEntity kEntity) {
        Long value = this.entityVariableService.getLongValue(kEntity, CoreConstants.VAR_SETTINGS_DEFAULT_ID);
        System.err.println("<===getDefaultAppSetings : " + value + "this.appSettingsService.getOne(value)" + this.appSettingsService.getOne(value) + " ===>");
        return this.appSettingsService.getOne(value);
    }

    public List<EntiteSettings> sortedList(List<EntiteSettings> list, Long id) {
        return list.
                stream()
                .sorted(Comparator.comparingInt(
                        item -> item.getId() == id ? 0 : 1
                ))
                .collect(Collectors.toList());
    }

    @Override
    public EntiteSettings recupererUnSettingsParEntite(Integer id) {
        LOGGER.log(Level.SEVERE, "L''entit\u00e9 settings r\u00e9cup\u00e9r\u00e9e lors de la mise \u00e0 jour de l''entit\u00e9 {0}", this.dao.recupererUnSettingsParEntite(id));
        return this.dao.recupererUnSettingsParEntite(id);
    }

    @Override
    public Settings getDefaultOcurrence(Long id) {
//        return this.dao.getDefaultOcurrence(id);
//        System.err.println("Default setting ocurence : " + this.settingService.getOne(id));
//        System.err.println("Default setting ocurence cast : " + (EntiteSettings) this.settingService.getOne(id));
        return this.dao.getDefaultOcurrence(id);
    }
}
