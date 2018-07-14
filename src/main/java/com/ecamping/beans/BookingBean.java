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
import com.ecamping.service.CampingService;
import com.ecamping.service.UserService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author isabella
 */
@ManagedBean(name = "bookingBean")
@SessionScoped
public class BookingBean implements Serializable {

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

    @EJB
    private CampingService campingService;

    @PostConstruct
    public void iniciar() {
        booking = bookingService.create();
        user = userService.create();
        camping = (Camping) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("camping");
        cpf = new String();
    }

    public User pegarCampista(String cpf) {
        User campista = userService.getUserPorCPF(cpf);
        return campista;
    }

    public String criarReserva() {

        Booking newBooking = new Booking();
        User userBooking = userService.getUserPorCPF(this.cpf);
        newBooking.setUser(userBooking);
        Camping campingBooking = campingService.getCampingsPorNome(camping.getName());
        newBooking.setCamping(campingBooking);
        newBooking.setBookingDate(this.booking.getBookingDate());
        newBooking.setTent(this.booking.getTent());
        addMessage(newBooking.getBookingDate() + " " + newBooking.getTent());
        this.bookingService.persistence(newBooking);

        addMessage("Reserva cadastrado com sucesso!");
        return "index?faces-redirect=true";
    }

    private Date getData(Integer dia, Integer mes, Integer ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        return c.getTime();
    }

    public List<Booking> getReservas() {
        return reservas;
    }

    public String MudarDePagina(Camping camping) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("camping", camping);
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

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
