/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.beans;

import com.ecamping.entidade.User;
import com.ecamping.service.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author isabella
 */
@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean implements Serializable {

    private User usuario;

    @EJB
    UserService userService;

    @PostConstruct
    public void iniciar() {
        usuario = userService.create();
    }

    public String salvar() {
        this.userService.persistence(this.usuario);
        this.usuario = new User();
        addMessage("Usuario cadastrado com sucesso!");
        return "index?faces-redirect=true";
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
    
    public User findUser(String cpf){
        return usuario = userService.getUserPorCPF(cpf);
    }
     public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
}
