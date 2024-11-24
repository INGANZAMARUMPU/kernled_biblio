/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.converter;

import com.mediasofthome.biblio.domain.MembreVo;
import com.mediasofthome.biblio.service.MembreServiceBeanLocal;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

/**
 *
 * @author INGANZAMARUMPU
 */
@Named
@FacesConverter(value = "membreVoConverter", managed = true)
public class MembreVoConverter implements Converter<MembreVo> {
    
    @EJB
    MembreServiceBeanLocal membreService;

    @Override
    public MembreVo getAsObject(FacesContext fc, UIComponent uic, String id) {
        return membreService.chargerMembreVoById(Long.valueOf(id));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, MembreVo t) {
        return t.toString();
    }
}
