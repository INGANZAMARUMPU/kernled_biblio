package com.mediasoftstage.biblio.dao;

import com.mediasoftstage.biblio.entities.Reservation;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;

/**
 *
 * @author INGANZAMARUMPU
 */

@Stateless
public class ReservationDaoBean extends GenericDAOBean<Reservation, Integer> {

    public ReservationDaoBean() {
        super(Reservation.class);
    }
}
