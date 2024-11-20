/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mediasofthome.biblio.core.dao;

import com.mediasofthome.krnl.dao.GenericDAOBean;
import com.mediasofthome.krnl.entities.Settings;
import com.mediasofthome.biblio.core.entities.Entite;
import com.mediasofthome.biblio.core.entities.EntiteSettings;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author Mediasoft
 */
@Stateless
public class EntiteSettingsDaoBean extends GenericDAOBean<EntiteSettings, Long> {

    public EntiteSettingsDaoBean() {
        super(EntiteSettings.class);
    }

    public Settings getDefaultOcurrence(Long id) {
        String jpql = "SELECT s FROM Settings s WHERE s.id =:id ";
        TypedQuery<Settings> query = this.em.createQuery(jpql, Settings.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

//    public Settings recupererUnSettingsParEntite(Integer id) {
//        try {
//            String jpql = """
//                            select * from core_settings s where s.dtype = 'EES' and s.entity_id = id
//                          """;
//            Query query = this.em.createNativeQuery(jpql, Settings.class);
//            query.setParameter("entity_id", id);
//            return (Settings) query.getSingleResult();
//        } catch (NoResultException nre) {
//            return null;
//        }
//    }
    public EntiteSettings recupererUnSettingsParEntite(Integer id) {
        try {
            String jpql = "SELECT e FROM EntiteSettings e "
                    + "LEFT JOIN FETCH e.entity "
                    + "WHERE e.entity.id = :id";
            Query query = this.em.createQuery(jpql);
            query.setParameter("id", id);
            return (EntiteSettings) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
