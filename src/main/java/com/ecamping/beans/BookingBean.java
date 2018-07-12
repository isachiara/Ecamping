/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.beans;

import com.ecamping.entidade.Booking;
import com.ecamping.entidade.Camping;
import com.ecamping.entidade.User;
import com.ecamping.service.BookingService;
import com.ecamping.service.UserService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author isabella
 */
@ManagedBean(name = "bookingBean")
@SessionScoped
public class BookingBean implements Serializable{

    private List<Booking> reservas;
    private User user;
    private String cpf;
    private Camping camping;
    private Booking booking;
    String reservaEfetuada = "Reserva efetuada com sucesso!";
    
    @EJB
    private UserService userService;

    @EJB
    private BookingService bookingService;

    @PostConstruct
    public void iniciar() {
        booking = bookingService.create();
        user = userService.create();
        camping = new Camping();
    }

    public void criarReserva() {
        this.user = userService.getUserPorCPF(cpf);
        this.booking.setUser(this.user);
        this.booking.setCamping(this.camping);
        this.bookingService.persistence(this.booking);
        this.booking = new Booking();
        //return reservaEfetuada;
    }

    public List<Booking> getReservas() {
        return reservas;
    }

    public String MudarDePagina(Camping camping) {
        this.camping = camping;
        return "reserva?faces-redirect=true";
    }

    public void setReservas(String camping) {
        this.reservas = bookingService.getBookingPorCamping(camping);
    }

    public String getMessage() {
        return reservaEfetuada;
    }

    public void setMessage(String message) {
        this.reservaEfetuada = message;
    }

    public Camping getCamping() {
        return camping;
    }

    public void setCamping(Camping camping) {
        this.camping = camping;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getReservaEfetuada() {
        return reservaEfetuada;
    }

    public void setReservaEfetuada(String reservaEfetuada) {
        this.reservaEfetuada = reservaEfetuada;
    }
    
    
}
