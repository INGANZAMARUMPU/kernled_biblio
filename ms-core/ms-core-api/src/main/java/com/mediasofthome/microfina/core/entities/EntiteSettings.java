/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.entities;

import com.mediasofthome.krnl.entities.AppSettings;
import com.mediasofthome.krnl.entities.EntitySettings;
import com.mediasofthome.krnl.entities.KEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author Sama E. S.
 */
@Entity
@DiscriminatorValue("EES")
public class EntiteSettings extends EntitySettings {

    @Column(name = "mode_ouverture")
    private boolean modeOverture;

    @Column(name = "nombre_max_jrnee_ouvertes")
    private Integer nmbreJrneeMaxOuvertes;

    public EntiteSettings() {

    }

    public EntiteSettings(AppSettings e) {
        super(e);
    }

    public EntiteSettings(boolean modeOverture, Integer nmbreJrneeMaxOuvertes) {
        this.modeOverture = modeOverture;
        this.nmbreJrneeMaxOuvertes = nmbreJrneeMaxOuvertes;
    }

    public static EntiteSettings getDefault(AppSettings settings, KEntity entity) {
        EntiteSettings entiteSettings = new EntiteSettings(settings);
        entiteSettings.setEntity(entity);
        entiteSettings.buildDefault();
        return entiteSettings;
    }

    public boolean isModeOverture() {
        return modeOverture;
    }

    public void setModeOverture(boolean modeOverture) {
        this.modeOverture = modeOverture;
    }

    public Integer getNmbreJrneeMaxOuvertes() {
        return nmbreJrneeMaxOuvertes;
    }

    public void setNmbreJrneeMaxOuvertes(Integer nmbreJrneeMaxOuvertes) {
        this.nmbreJrneeMaxOuvertes = nmbreJrneeMaxOuvertes;
    }

    @Override
    public String toString() {
        return "EntiteSettings{" + "modeOverture=" + modeOverture + ", nmbreJrneeMaxOuvertes=" + nmbreJrneeMaxOuvertes + '}';
    }

}
