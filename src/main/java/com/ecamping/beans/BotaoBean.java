/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author isabella
 */
@ManagedBean(name = "botaoBean")
@RequestScoped
public class BotaoBean {

    public String cadastrarCamping() {
        return "/cadastroCamping";
    }

    public String cadastrarCampista() {
        return "/cadastroCampista";
    }

    public String paginaCamping() {
        return "/paginaCamping";
    }
    
    public String reserva() {
        return "/reserva";
    }
    
    public String listaCampistas(){
        return "/listaCampistas";
    }
    
   

}
