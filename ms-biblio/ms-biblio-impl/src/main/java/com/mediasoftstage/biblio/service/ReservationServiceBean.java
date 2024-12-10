package com.mediasoftstage.biblio.service;

import com.mediasoftstage.biblio.dao.ReservationDaoBean;
import com.mediasoftstage.biblio.entities.Reservation;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.service.GenericServiceBean;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */
@Stateless
public class ReservationServiceBean extends GenericServiceBean<Reservation, Integer> implements ReservationBeanLocal {

    @EJB
    private ReservationDaoBean dao;

    @Override
    protected GenericDAOBean<Reservation, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(Reservation e) {
        return e.getId();
    }

}
