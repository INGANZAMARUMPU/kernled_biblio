package com.mediasofthome.biblio.dao;

import com.mediasofthome.biblio.entities.ActeurTypeOperation;
import com.mediasofthome.krnl.dao.GenericDAOBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;

import java.util.List;

/**
 *
 * @author SAMIÈ Pyabalo
 * @since Mardi, 25 juillet 2023 à 10:15
 */
@Stateless
public class ActeurTypeOperationDaoBean extends GenericDAOBean<ActeurTypeOperation, Long> {

    public ActeurTypeOperationDaoBean() {
        super(ActeurTypeOperation.class);
    }

    public List<ActeurTypeOperation> getAllByProcuration(Long id) {
        String jpql = "SELECT a FROM ActeurTypeOperation a "
                + "LEFT JOIN FETCH a.typeOperation "
                + "LEFT JOIN FETCH a.acteur "
                + "LEFT JOIN FETCH a.procuration "
                + "WHERE a.procuration.id = :id ";
        Query query = em.createQuery(jpql);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
