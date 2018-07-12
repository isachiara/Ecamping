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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
    
 
}
