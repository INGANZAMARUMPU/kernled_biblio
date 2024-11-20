/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mediasofthome.biblio.core.service;

import com.mediasofthome.krnl.entities.User;
import com.mediasofthome.krnl.service.GenericServiceBeanLocal;
import com.mediasofthome.biblio.core.entities.Employe;
import org.primefaces.event.UnselectEvent;

import java.util.List;

/**
 *
 * @author Mediasoft
 */
public interface EmployeServiceBeanLocal extends GenericServiceBeanLocal<Employe, Long> {

    public void rolesSelected(UnselectEvent event);

    public List<User> getAllUser(String prefixe);
}
