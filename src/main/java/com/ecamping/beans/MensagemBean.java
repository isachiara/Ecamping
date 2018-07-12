/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author isabella
 */
@ManagedBean(name = "mensagemBean")
public class MensagemBean {
   
     public void onToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage("Cadastro feito com sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
