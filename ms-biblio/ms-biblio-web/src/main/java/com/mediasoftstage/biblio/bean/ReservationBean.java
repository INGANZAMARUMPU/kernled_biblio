/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasoftstage.biblio.bean;

import com.mediasoftstage.biblio.constants.BiblioPermissionConstants;
import com.mediasoftstage.biblio.entities.Reservation;
import com.mediasoftstage.biblio.service.ReservationBeanLocal;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.krnl.web.beans.GenericCrudBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author INGANZAMARUMPU
 */
@Named
@ViewScoped
public class ReservationBean extends GenericCrudBean<Reservation, Integer> {

    @EJB
    protected ReservationBeanLocal reservation_bean_local;
    
    private List<Reservation> reservations;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Reservation();
        this.reservations = reservation_bean_local.getAll();
    }

    @Override
    public boolean canAdd() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_RESERVATION_ADD) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_RESERVATION_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public boolean canUpdate() {
        return 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_RESERVATION_EDIT) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_RESERVATION_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    public boolean canDelete() {
        return
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_RESERVATION_DELETE) || 
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_RESERVATION_ALL) ||
            this.userService.isPermitted(BiblioPermissionConstants.PERM_BIBLIO_ALL);
    }

    @Override
    protected GenericServiceBeanLocal<Reservation, Integer> getService() {
        return this.reservation_bean_local;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

}
