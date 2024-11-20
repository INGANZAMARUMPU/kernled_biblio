/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.bean;

import com.mediasofthome.biblio.domain.MembreStats;
import com.mediasofthome.biblio.domain.MembreVo;
import com.mediasofthome.biblio.entities.CompteEpargne;
import com.mediasofthome.biblio.service.CompteEpargneServiceBeanLocal;
import com.mediasofthome.biblio.service.MembreServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.UserSessionBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mediasoft
 */
@Named
@ViewScoped
public class DashbordBean implements Serializable {

    @EJB
    private MembreServiceBeanLocal membreService;
    @EJB
    private CompteEpargneServiceBeanLocal compteEpargneService;

    @Inject
    private UserSessionBean userSession;

    private List<MembreVo> membres;
    private MembreStats stat;
    private Long totalMembre;
    private Long nombreDeCompteTotal;

    private CompteEpargne compteEpargne;

    @PostConstruct
    public void initList() {
        this.compteEpargne = new CompteEpargne();
        this.stat = this.membreService.chargerMembreStats(null, false);
        this.totalMembre = this.getStat().getPhysique() + this.getStat().getGroupement() + this.getStat().getEntreprise();
        this.nombreDeCompteTotal = this.compteEpargneService.count();
    }

    public Long getTotalMembre() {
        return totalMembre;
    }

    public void setTotalMembre(Long totalMembre) {
        this.totalMembre = totalMembre;
    }

    public CompteEpargne getCompteEpargne() {
        return compteEpargne;
    }

    public void setCompteEpargne(CompteEpargne compteEpargne) {
        this.compteEpargne = compteEpargne;
    }

    public Long getNombreDeCompteTotal() {
        return nombreDeCompteTotal;
    }

    public void setNombreDeCompteTotal(Long nombreDeCompteTotal) {
        this.nombreDeCompteTotal = nombreDeCompteTotal;
    }

    public MembreStats getStat() {
        if (Objects.isNull(stat)) {
            stat = new MembreStats();
        }
        return stat;
    }

    public void setStat(MembreStats stat) {
        this.stat = stat;
    }

    public UserSessionBean getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSessionBean userSession) {
        this.userSession = userSession;
    }

    public List<MembreVo> getMembres() {
        return membres;
    }

    public void setMembres(List<MembreVo> membres) {
        this.membres = membres;
    }

}
