/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.core.service;

import com.mediasofthome.krnl.entities.AppSettings;
import com.mediasofthome.krnl.entities.KEntity;
import com.mediasofthome.krnl.entities.Settings;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.biblio.core.entities.EntiteSettings;

import java.util.List;

/**
 *
 * @author Mediasoft
 */
public interface EntiteSettingServiceBeanLocal extends GenericServiceBeanLocal<EntiteSettings, Long> {

    public Settings getDefaultOcurrence(Long id);

    public List<EntiteSettings> sortedList(List<EntiteSettings> list, Long id);

    public AppSettings getDefaultAppSetings(KEntity kEntity);

    EntiteSettings recupererUnSettingsParEntite(Integer id);
}
