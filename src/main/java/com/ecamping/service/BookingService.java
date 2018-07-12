/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.service;

import com.ecamping.entidade.Booking;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

/**
 *
 * @author isabella
 */
@Stateless(name = "ejb/BookingService")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class BookingService extends Service<Booking> {

    @PostConstruct
    public void init() {
        super.setClasse(Booking.class);
    }

    @Override
    public Booking create() {
        return new Booking();
    }
    
    @Override
    public boolean exist(@NotNull Booking booking) {
        TypedQuery<Booking> query
                = entityManager.createNamedQuery(Booking.BOOKING_POR_ID, classe);
        query.setParameter(1, booking.getId());
        return !query.getResultList().isEmpty();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Booking> getBookingPorData(Date data1, Date data2){
        return super.findEntities(new Object[]{data1, data2}, Booking.BOOKING_POR_DATA);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Booking> getBookingPorUser(String usuario){
        return super.findEntities(new Object[]{usuario}, Booking.BOOKING_POR_USER);
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Booking> getBookingPorCamping(String camping){
        return super.findEntities(new Object[]{camping}, Booking.BOOKING_POR_NOME_CAMPING);
    }
    
}
